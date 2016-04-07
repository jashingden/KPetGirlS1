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

        public String[][] models;

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
            JSONArray array = obj.getJSONArray("Model");
            models = new String[array.length()][2];
            for (int i=0; i<array.length(); i++) {
                JSONObject m = array.getJSONObject(i);
                models[i][0] = m.getString("Model_ID");
                models[i][1] = m.getString("Model_Name")+"/"+m.getString("Open_Flag")+"/"+m.getString("Category");
            }
        }
    }

    public static class GetRoleFiles extends MyTask {

        public String[] Video;
        public String[] Preview;
        public String[] OpenFlag;
        public String Image;
        public String[] Info;

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
            JSONObject movies = obj.getJSONObject("Movies");
            for (int i=10; i>=1; i--) {
                String num = Integer.toString(i);
                String video = movies.optString("Video_File" + num, "");
                String preview = movies.optString("Video_PreviewImage"+num, "");
                String open = movies.optString("Video"+num+"_Open_Flag", "");
                if (video.length() > 0 || preview.length() > 0) {
                    if (video.length() > 0) {
                        if (Video == null) Video = new String[i];
                        Video[i-1] = video;
                    }
                    if (preview.length() > 0) {
                        if (Preview == null) Preview = new String[i];
                        Preview[i-1] = preview;
                    }
                    if (open.length() > 0) {
                        if (OpenFlag == null) OpenFlag = new String[i];
                        OpenFlag[i-1] = open;
                    }
                }
            }

            JSONObject images = obj.getJSONObject("Images");
            Image = images.getString("Image_Unlock");

            Info = new String[6];
            Info[0] = obj.optString("Model_ID", "")+"/"+obj.optString("Category", "");
            Info[1] = obj.optString("Model_Name", "");
            Info[2] = "年齡:"+obj.optString("Year", "");
            Info[3] = "身高:"+obj.optString("Height", "");
            Info[4] = "體重:"+obj.optString("Weight", "");
            Info[5] = "三圍:"+obj.optString("BWH", "");
        }
    }

    public static class GetUnFinishQuestList extends MyTask {

        public boolean PrizeByHour_Is_Finish;
        public boolean DailyLogin_Is_Finish;

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
                String Quest_ID = obj.optString("Quest_ID", "");
                if (Quest_ID.equals("DailyLogin")) {
                    DailyLogin_Is_Finish = obj.optBoolean("Is_Finish", false);
                } else if (Quest_ID.equals("PrizeByHour")) {
                    PrizeByHour_Is_Finish = obj.optBoolean("Is_Finish", false);
                }
            }
        }
    }

    public static class SendQuestFinish extends MyTask {

        private String Quest_ID;

        public SendQuestFinish(DiceGirl dice, String questID) {
            super(dice);
            jsp = "SVC_Dice_Baseball.jsp";
            type = "sendQuestFinish";
            Quest_ID = questID;
            nameValuePair = new ArrayList<NameValuePair>();
            nameValuePair.add(new BasicNameValuePair("type", type));
            nameValuePair.add(new BasicNameValuePair("uid", dice.uid));
            nameValuePair.add(new BasicNameValuePair("Token", dice.token));
            nameValuePair.add(new BasicNameValuePair("Quest_ID", Quest_ID));
        }

        public String getQuestID() {
            return Quest_ID;
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
