package com.yrkj.controller;

import com.alibaba.fastjson.JSONObject;
import com.yrkj.config.HttpUtils;
import com.yrkj.controller.Inputs.JsPayInput;
import com.yrkj.controller.Inputs.OrderInput;
import com.yrkj.mapper.OrderMapper;
import com.yrkj.mapper.UserProductMapper;
import com.yrkj.model.UserProduct.PayProductInput;
import com.yrkj.model.core.ActionResult;
import com.yrkj.model.core.PageModel;
import com.yrkj.model.order.Order;
import com.yrkj.model.order.WXOrderSearch;
import com.yrkj.service.OrderService;
import com.yrkj.service.UserProductService;
import com.yrkj.utils.DatetimeUtil;
import com.yrkj.utils.Md5Utils;
import com.yrkj.utils.RandomUtil;
import com.yrkj.utils.XmlJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/pay")
@EnableSwagger2
@Api(description = "微信支付接口")
public class PayController   {

    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.secret}")
    private String secret;
    @Value("${weixin.saleId}")
    private String saleId;
    @Value("${weixin.saleKey}")
    private String saleKey;

    @Autowired
    private UserProductService userProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserProductMapper userProductMapper;

    @Autowired
    private OrderMapper orderMapper;

    @ApiOperation(value="创建订单", notes="创建订单")
    @RequestMapping(value  ="/commitOrder" ,method = RequestMethod.POST)
    public ActionResult  commitOrder(@RequestBody OrderInput input){

        //处理价格单位为：分(请自行处理)
        float productPrice = 0f;

        for (PayProductInput temp:input.getList()){
            PayProductInput rest = userProductMapper.selectProductInfo(temp.getProduct_id());
            Float price = rest.getPrice();
            temp.setPrice(price);
            temp.setProduct_name(rest.getProduct_name());
            productPrice= productPrice + price * temp.getNumber();
        }

        //数据库生成订单
        Order order = new Order();

        String nom = getOrder_NO();
        order.setOrder_num(nom);
        order.setOrder_state(0);
        order.setOpen_id(input.getOpen_id());
        order.setProduct_cost(productPrice);
        order.setList(input.getList());
        order.setCreate_time(new Date());

        //生成订单
        return orderService.createOrder(order);
    }

    @ApiOperation(value="查询订单详情", notes="查询订单详情")
    @RequestMapping(value  ="/getOrderById" ,method = RequestMethod.GET)
    public ActionResult  getOrderById(@RequestParam Long id){
        //查询订单
        return orderService.getOrderById(id);
    }

    @ApiOperation(value="更新订单地址", notes="更新订单地址")
    @RequestMapping(value  ="/updateOrderAddress" ,method = RequestMethod.POST)
    public ActionResult updateOrderAddress(@RequestBody Order order){
        return orderService.updateOrderAddress(order);
    }


    @ApiOperation(value = "获取微信用户订单列表",notes = "获取微信用户订单列表")
    @RequestMapping(value = "/payList", method = RequestMethod.POST)
    public PageModel list(@RequestBody WXOrderSearch model) {
        return orderService.getWxOrderList(model);
    }

    @ApiOperation(value = "获取积分订单列表",notes = "获取积分订单列表")
    @RequestMapping(value = "/integralList", method = RequestMethod.POST)
    public PageModel integralList(@RequestBody WXOrderSearch model) {
        return orderService.getIntegralOrderList(model);
    }

    @ApiOperation(value = "获取积分订单详情",notes = "获取积分订单列表")
    @RequestMapping(value = "/integralDetail", method = RequestMethod.GET)
    public ActionResult integralList(@RequestParam Long orderId) {
        return orderService.GetIntegralOrderDetail(orderId);
    }

    public static String getOrder_NO() {
        return "JG" + DatetimeUtil.formatDate(new Date(), DatetimeUtil.TIME_STAMP_PATTERN);
    }

    /**
     * 微信js预支付接口*/
    @ApiOperation(value="微信js预支付接口", notes="微信支付接口")
    @ApiImplicitParam(name = "input", value = "dto对象", required = true, dataType = "JsPayInput")
    @RequestMapping(value  ="/jspay" ,method = RequestMethod.POST)
    public ActionResult jspay (@RequestBody JsPayInput input){

        try {

            Order order = orderMapper.selectOrder(input.getOrder_id());

            float product_cost = order.getProduct_cost();
            float courier_cost = input.getCourier_cost();
            Integer totalPrice = 0;

            if (product_cost >= 300){
                totalPrice = (int)(product_cost * 100);
            }else {
                totalPrice = (int)(product_cost * 100) + (int) (courier_cost * 100);
            }

            String  WIDtotal_fee= Integer.toString(totalPrice);
            String nom = order.getOrder_num();
            String prepay_id = getPrepayid(nom, WIDtotal_fee, order.getOpen_id(),input.getRedirect_url(),input.getUser_ip());//获取预支付标示
            if (prepay_id==null||prepay_id.isEmpty()){
                return  new ActionResult(false,"生成预支付定单失败");
            }

            order.setProvince_id(input.getProvince_id());
            order.setProvince_name(input.getProvince_name());
            order.setCity_id(input.getCity_id());
            order.setCity_name(input.getCity_name());
            order.setAddress(input.getAddress());
            order.setReceiver(input.getReceiver());
            order.setPhone(input.getPhone());
            order.setCourier_cost(input.getCourier_cost());

            order.setOrder_num(nom);

            //更新微信订单号订单号
            orderService.updateOrder(order);

            //组装map用于生成sign
            Map<String, String> result=new HashMap<String, String>();
            result.put("appId", appId);
            //时间戳
            String timeStamp=(System.currentTimeMillis()/1000)+"";
            result.put("timeStamp", timeStamp);
            //随机字符串
            String nonceStr= RandomUtil.generateLowerString(16);
            result.put("nonceStr", nonceStr);
            //预支付标识
            result.put("prepay_id",prepay_id);
            //金额
            result.put("total_fee",WIDtotal_fee);
            //加密方式
            result.put("signType", "MD5");
            //组装map用于生成sign
            Map<String, String> map=new HashMap<String, String>();
            map.put("appId", appId);
            map.put("timeStamp", timeStamp);
            map.put("nonceStr", nonceStr);
            map.put("package", "prepay_id="+prepay_id);
            map.put("signType", "MD5");
            result.put("paySign", Md5Utils.sign(map,saleKey).toUpperCase());//签名
            result.put("order", nom);
            return new ActionResult(result);

        }catch (Exception e){
            return  new ActionResult(false,e.getMessage());
        }
    }

    /**
     * 为新订单查询接口
     **/
    @ApiOperation(value="为新订单查询接口", notes="微信支付接口")
    @RequestMapping(value  ="/search" ,method = RequestMethod.POST)
    public  ActionResult FindOrder(String out_trade_no,Integer price){
        ActionResult result ;
        String url="https://api.mch.weixin.qq.com/pay/orderquery";
        String nonce_str = RandomUtil.generateLowerString(16);//生成随机数，可直接用系统提供的方法
        String body = "vote-商品订单";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("appid", appId);
        map.put("mch_id", saleId);
        map.put("nonce_str", nonce_str);
        map.put("out_trade_no", out_trade_no);
        String sign = Md5Utils.sign(map,saleKey).toUpperCase();//参数加密
        map.put("sign", sign);
        //组装xml(wx就这么变态，非得加点xml在里面)
        String content= Md5Utils.MapToXmlNoReg(map);
        //System.out.println(content);
        String PostResult= HttpUtils.post(url, content);
        try{
            JSONObject jsonObject= XmlJsonUtil.xml2Json(PostResult);//返回的的结果

            result=new ActionResult(jsonObject);
        }catch (Exception e){
            result=new ActionResult(false,e.getMessage());
        }
        return  result;
    }

    /**
     * 获取微信与支付订单
     **/
    private String getPrepayid(String out_trade_no1,String total_fee1,String openid1,String redirt,String userIp) throws  Exception{
        String result = "";
        String appid = appId;
        String mch_id = saleId;
        String nonce_str = RandomUtil.generateLowerString(16);//生成随机数，可直接用系统提供的方法
        String body = "vote-商品订单";
        String out_trade_no = out_trade_no1;
        String total_fee = total_fee1;
        String spbill_create_ip = userIp;//用户端ip,这里随意输入的
        String notify_url = redirt;//支付回调地址
        String trade_type = "JSAPI";
        String openid = openid1;

        HashMap<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("attach", "test");
        map.put("device_info", "WEB");
        map.put("nonce_str", nonce_str);
        String tempBody= XmlJsonUtil.getUTF8XMLString(body);
        map.put("body",tempBody);
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", spbill_create_ip);
        map.put("trade_type", trade_type);
        map.put("notify_url", notify_url);
        map.put("openid", openid);
        String sign = Md5Utils.sign(map,saleKey).toUpperCase();//参数加密
        //  System.out.println("sign秘钥:-----------"+sign);
        map.put("sign", sign);
        //组装xml(wx就这么变态，非得加点xml在里面)
        String content= Md5Utils.MapToXmlNoReg(map);
        //System.out.println(content);
        String PostResult= HttpUtils.post("https://api.mch.weixin.qq.com/pay/unifiedorder", content);
        try{
            JSONObject jsonObject= XmlJsonUtil.xml2Json(PostResult);//返回的的结果
            if(jsonObject.getString("return_code").equals("SUCCESS")&&jsonObject.getString("result_code").equals("SUCCESS")){
                result=jsonObject.get("prepay_id")+"";//这就是预支付id
            }
            return result;
        }catch (Exception e){
         //   logger.error(e.getMessage(),e);
            return  e.getMessage();
        }


    }



    @RequestMapping(value = "notify" ,method = RequestMethod.POST)
    public void notify(HttpServletRequest request,HttpServletResponse response) throws Exception {
        // System.out.println("----接收到的数据如下：---" + request);
        //读取参数
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        inputStream = request.getInputStream();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();
        //------------------------------
        //处理业务开始
        //------------------------------
        String resXml;
        if (sb.toString().isEmpty()) {
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }else   {
            //解析xml成map
            JSONObject m;
            System.out.println(sb.toString());
            m = XmlJsonUtil.xml2Json(sb.toString());
            if ("SUCCESS".equals(m.getString("result_code"))) {
                String out_trade_no =  m.getString("out_trade_no");
                //修改订单状态
                orderService.finishOrder(out_trade_no);
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
        }

        //------------------------------
        //处理业务完毕
        //------------------------------
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }

    @ApiOperation(value="测试订单完成接口", notes="测试订单完成接口")
    @RequestMapping(value  ="/testFinishOrder" ,method = RequestMethod.POST)
    public ActionResult testFinishOrder(@RequestParam String order_num){
        return orderService.finishOrder(order_num);
    }
}


