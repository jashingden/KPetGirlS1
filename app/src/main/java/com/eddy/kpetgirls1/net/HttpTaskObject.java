package com.eddy.kpetgirls1.net;

import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;

public class HttpTaskObject
{
  private String url;

  public HttpTaskObject(String paramString1, String paramString2)
  {
    this.url = (paramString1 + paramString2);
  }

  public ArrayList<BasicNameValuePair> getData()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("noCache", String.valueOf(Math.random())));
    return localArrayList;
  }

  public String getUrl()
  {
    return this.url;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\classes_dex2jar.jar
 * Qualified Name:     com.rooex.net.HttpTaskObject
 * JD-Core Version:    0.5.4
 */