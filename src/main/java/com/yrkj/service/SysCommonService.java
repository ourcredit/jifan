package com.yrkj.service;

import com.yrkj.mapper.SysCommonMapper;
import com.yrkj.model.SysCommon.MessageCode;
import com.yrkj.model.User.User;
import com.yrkj.model.core.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by xuenianxiang on 2017/7/23.
 */
@Service
public class SysCommonService {

    @Autowired
    private SysCommonMapper sysCommonMapper;

    public ActionResult sendMessage(String mobile, String open_id) throws Exception {

        if (!isChinaPhoneLegal(mobile)){
            return new ActionResult(false,null,"请输入正确手机号");
        }

        // 用户名
        String name = "15810191986";
        // 密码
        String pwd = "C7B14277CD112C6D5D22352236E2";
        // 电话号码字符串，中间用英文逗号间隔
        StringBuffer mobileString = new StringBuffer(mobile);
        // 内容字符串

        int code = (int) ((Math.random() * 9 + 1) * 1000);
        String codeStr = String.valueOf(code);

        StringBuffer contextString = new StringBuffer("您的验证码为：" + codeStr + "，如非本人操作，请忽略");
        // 签名
        String sign = "汲古科技";
        // 追加发送时间，可为空，为空为及时发送
        String stime = "";
        // 扩展码，必须为数字 可为空
        StringBuffer extno = new StringBuffer();

        Date now = new Date();

        MessageCode mc = new MessageCode();
        mc.setMobile(mobile);
        mc.setCode(codeStr);
        mc.setCreate_time(now);

        List<MessageCode> list = sysCommonMapper.selectMessageCode(mc);

        int a = 0;
        int b = 0;
        int c = 0;

        for (MessageCode temp : list) {
            if ((now.getTime() - temp.getCreate_time().getTime()) / 1000 < 60) {
                a++;
                break;
            }

            if ((now.getTime() - temp.getCreate_time().getTime()) / 1000 < 3600) {
                b++;
            }

            c++;
        }

        if (a > 0) {
            return new ActionResult(false, null, "一个号码一分钟以内只能发1条");
        }

        if (b > 4) {
            return new ActionResult(false, null, "一个号码一小时以内最多能发5条");
        }

        if (c > 9) {
            return new ActionResult(false, null, "一个号码一天最多能发10条");
        }


        sysCommonMapper.insertMessageCode(mc);

        //doPost(name, pwd, mobileString, contextString, sign, stime, extno);

        return new ActionResult(true, codeStr, "发送成功");
    }

    //绑定手机号
    public ActionResult bindMobile(String mobile, String open_id, String code) {

        //判断验证码
        MessageCode mc = new MessageCode();
        mc.setMobile(mobile);
        mc.setCreate_time(new Date());

        MessageCode codeModel = sysCommonMapper.selectLastMessageCode(mc);

        if (codeModel != null && code.equals(codeModel.getCode())) {

            //绑定手机号
            User user = new User();
            user.setOpen_id(open_id);
            user.setMobile(mobile);

            sysCommonMapper.bindModile(user);

            return new ActionResult(true, "", "绑定成功");

        } else {

            return new ActionResult(false, null, "验证码无效");
        }

    }


    public String doPost(String name, String pwd,
                         StringBuffer mobileString, StringBuffer contextString,
                         String sign, String stime, StringBuffer extno) throws Exception {
        StringBuffer param = new StringBuffer();
        param.append("name=" + name);
        param.append("&pwd=" + pwd);
        param.append("&mobile=").append(mobileString);
        param.append("&content=").append(URLEncoder.encode(contextString.toString(), "UTF-8"));
        param.append("&stime=" + stime);
        param.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
        param.append("&type=pt");
        param.append("&extno=").append(extno);

        URL localURL = new URL("http://web.wasun.cn/asmx/smsservice.aspx?");
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(param.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String resultBuffer = "";

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(param.toString());
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            resultBuffer = convertStreamToString(inputStream);

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        return resultBuffer;
    }


    /**
     * 转换返回值类型为UTF-8格式.
     *
     * @param is
     * @return
     */
    public String convertStreamToString(InputStream is) {
        StringBuilder sb1 = new StringBuilder();
        byte[] bytes = new byte[4096];
        int size = 0;

        try {
            while ((size = is.read(bytes)) > 0) {
                String str = new String(bytes, 0, size, "UTF-8");
                sb1.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb1.toString();
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}