package com.developer.mabdullahk.mccwarehouse;
public class warehouseAdd
{
  String contactperson;
  String facesize;
  String id;
  String location;
  String name;
  String phone;
  String validity;
  String latlng;

  public warehouseAdd() {}

  public warehouseAdd(String namee, String locatione, String contactpersone, String phonee, String ide, String validitye, String facesizee)
  {
    name = namee;
    phone = phonee;
    location = locatione;
    contactperson = contactpersone;
    id = ide;
    facesize = facesizee;
    validity = validitye;
  }

  public String getContactperson()
  {
    return contactperson;
  }

  public String getId()
  {
    return id;
  }

  public String getFacesize()
  {
    return facesize;
  }

  public String getValidity()
  {
    return validity;
  }

  public String getLocation()
  {
    return location;
  }

  public String getName()
  {
    return name;
  }

  public String getPhone()
  {
    return phone;
  }
  public String getLatlng()
  {
    return latlng;
  }


}



/* Location:           Z:\Users\mabdullahk\Downloads\dex2jar-2.0\classes-dex2jar.jar

 * Qualified Name:     com.developer.mabdullahk.mccwarehouse.warehouseAdd

 * JD-Core Version:    0.7.0.1

 */