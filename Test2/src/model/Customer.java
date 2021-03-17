package model;

public class Customer 
{
	private String _name;
    private String _pwd;
    private int _VIP;
    private int _point;
    
    public Customer()
    {
        _name = "Lai";
        _pwd = "0000";
        _VIP = 1;
        _point = 99999;
    }
    
    public Customer(String name,int vip,int pt)
    {
        _name = name;
        _VIP = vip;
        _point = pt;
    }
    
    public Customer(String name,String pwd,int vip,int pt)
    {
        _name = name;
        _pwd = pwd;
        _VIP = vip;
        _point = pt;
    }
    
    public String getName() {return _name;}
    public String getPWD() {return _pwd;}
    public int getVIP() {return _VIP;}
    public int getPoint() {return _point;}
    
    public int pay(int cost) 
    { 
        if(_VIP == 1) cost = (int)(cost * 0.8);
        //if(cost < _point)
            _point = _point - cost;
        /*else
            return -1;
        return 1;*/
        return 0;
    }
    
    public void changeVIP(int vip) {_VIP = vip; }
    
    //public void setName(String name) { _name = name; }
    //public void setPWD(String pwd) { _pwd = pwd; }
    //public void setVIP(int vip) { _VIP = vip; }
    //public void setPoint(int point) { _point = point; } 
	
	
	
}
