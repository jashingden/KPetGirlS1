package com.eddy.dicegirl;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by eddyteng on 2016/4/1.
 */
public class DiceGirl {

    public static String packageName = "com.yoho.playdicesp";
    public static String prefFilename = "com.yoho.playdicesp.xml";

    private Context mContext;

    private String uid;
    private String ua;
    private String bonus;
    private String password;
    private String token;
    private String mobileSN;
    private String mobileNO;

    public DiceGirl(Context context) {
        mContext = context;
    }

    public boolean readFile(String filename) {
        try {
            InputStream is = mContext.openFileInput(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            NodeList nodes = doc.getElementsByTagName("string");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element)nodes.item(i);
                String name = elem.getAttribute("name");
                String text = elem.getTextContent();
                if (name.equals("uid")) uid = text;
                else if (name.equals("ua")) ua = text;
                else if (name.equals("bonus")) bonus = text;
                else if (name.equals("password")) password = text;
                else if (name.equals("token")) token = text;
                else if (name.equals("mobileSN")) mobileSN = text;
                else if (name.equals("mobileNO")) mobileNO = text;
            }
            return true;
        } catch (Exception ex) {
            Log.d("Eddy", ex.getMessage());
        }
        return false;
    }

    public static class SendUserLogon extends MyTask {

        public SendUserLogon(DiceGirl dice) {
            super(dice);
            jsp = "SVC_Dice_Baseball_Auth.jsp";
            type = "sendUserLogon";
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("UA", dice.ua));
            nameValuePair.add(new BasicNameValuePair("MobileSN", dice.mobileSN));
            nameValuePair.add(new BasicNameValuePair("MobileNO", dice.mobileNO));
            nameValuePair.add(new BasicNameValuePair("UserPwd", dice.password));
        }

        protected void parseResult(JSONObject obj) throws Exception {
            dice.uid = obj.getString("uid");
            dice.token = obj.getString("token");
        }
    }

    public static class GetUserInfo extends MyTask {

        public GetUserInfo(DiceGirl dice) {
            super(dice);
            jsp = "SVC_Dice_Baseball.jsp";
            type = "getUserInfo";
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("uid", dice.uid));
            nameValuePair.add(new BasicNameValuePair("Token", dice.token));
        }

        @Override
        protected void parseResult(JSONObject obj) throws Exception {
        }
    }

    public static class GetRoleFiles extends MyTask {

        public GetRoleFiles(DiceGirl dice, String modelID) {
            super(dice);
            jsp = "SVC_Dice_Baseball.jsp";
            type = "getRoleFiles";
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("uid", dice.uid));
            nameValuePair.add(new BasicNameValuePair("Token", dice.token));
            nameValuePair.add(new BasicNameValuePair("Model_ID", modelID));
        }

        @Override
        protected void parseResult(JSONObject obj) throws Exception {
        }
    }

    public static class GetUnFinishQuestList extends MyTask {

        public boolean PrizeByHour_Is_Finish;

        public GetUnFinishQuestList(DiceGirl dice) {
            super(dice);
            jsp = "SVC_Dice_Baseball.jsp";
            type = "getUnFinishQuestList";
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("uid", dice.uid));
            nameValuePair.add(new BasicNameValuePair("Token", dice.token));
        }

        @Override
        protected void parseResult(JSONArray array) throws Exception {
            for (int i=0; i<array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                if (obj.optString("Quest_ID", "").equals("PrizeByHour")) {
                    PrizeByHour_Is_Finish = obj.optBoolean("Is_Finish", false);
                    break;
                }
            }
        }
    }

    public static class SendQuestFinish extends MyTask {

        public SendQuestFinish(DiceGirl dice, String questID) {
            super(dice);
            jsp = "SVC_Dice_Baseball.jsp";
            type = "sendQuestFinish";
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("uid", dice.uid));
            nameValuePair.add(new BasicNameValuePair("Token", dice.token));
            nameValuePair.add(new BasicNameValuePair("Quest_ID", questID));
        }

        @Override
        protected void parseResult(JSONObject obj) throws Exception {
        }
    }

    public static interface OnActionHandler {
        void onFinish(MyTask task, boolean bSuccess, String result);
    }

    public abstract static class MyTask implements Runnable {

        protected DiceGirl dice;
        protected String jsp;
        protected String type;
        protected List<NameValuePair> nameValuePair;
        protected OnActionHandler handler;
        protected String result;

        public MyTask(DiceGirl dice) {
            this.dice = dice;
        }

        public void start(OnActionHandler handler) {
            this.handler = handler;
            new Thread(this).start();
        }

        public String getType() {
            return type;
        }

        protected void parseResult(JSONObject obj) throws Exception {}
        protected void parseResult(JSONArray array) throws Exception {}

        @Override
        public void run() {
            testUrl();
        }

        private void testUrl() {
            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://yao1yao.myyoho.com/"+jsp);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                HttpResponse response = httpClient.execute(httpPost);
                result = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(result);
                boolean bSuccess = json.getBoolean("bSuccess");
                if (bSuccess) {
                    JSONArray array = json.getJSONArray("oResult");
                    if (array.length() == 1) {
                        parseResult(array.getJSONObject(0));
                    } else {
                        parseResult(array);
                    }
                }
                if (handler != null) handler.onFinish(this, bSuccess, result);
            } catch (Exception ex) {
                if (handler != null) handler.onFinish(this, false, ex.getMessage());
            }
        }

    }

}
