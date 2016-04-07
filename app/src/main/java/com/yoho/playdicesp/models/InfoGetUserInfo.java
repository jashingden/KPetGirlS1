package com.yoho.playdicesp.models;

public class InfoGetUserInfo
{
  public boolean bSuccess;
  public Result[] oResult;
  public String sMessage;

  public static class Result
  {
    public String Action_Point;
    public String Action_Time;
    public String Bonus;
    public int Experience;
    public int Experience_LevelUP;
    public int Experience_Starting;
    public models[] Model;
    public String Rank;
    public String RecoverAction;

    public static class models
    {
      public String Category;
      public String Model_ID;
      public String Model_Name;
      public String Open_Flag;
    }
  }
}

/* Location:           D:\GPhoneFans.net\Cracked\DiceGirl\com.yoho.playdicesp\classes_dex2jar.jar
 * Qualified Name:     com.yoho.playdicesp.models.InfoGetUserInfo
 * JD-Core Version:    0.5.4
 */