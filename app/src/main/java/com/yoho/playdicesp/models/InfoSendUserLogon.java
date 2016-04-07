package com.yoho.playdicesp.models;

public class InfoSendUserLogon
{
  public boolean bSuccess;
  public Result[] oResult;
  public String sMessage;

  public static class Result
  {
    public String token;
    public String uid;
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\DiceGirl\com.yoho.playdicesp\classes_dex2jar.jar
 * Qualified Name:     com.yoho.playdicesp.models.InfoSendUserLogon
 * JD-Core Version:    0.5.4
 */