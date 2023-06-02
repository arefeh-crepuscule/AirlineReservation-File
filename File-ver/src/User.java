public class User {
    private String username;
    private String password;
    private int charge = 0;
    private int notify =0;

    public User() {
    }
    public String getUsername() {
        return username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String toString() {
        return Menu.formatting(username) + Menu.formatting(password) + Menu.formatting(Integer.toString(charge))  + Menu.formatting(Integer.toString(notify)) ;
    }


    public User convertToObj(String obj){
        if(obj== null)return null;
        username=obj.substring(0, 20);
        password=obj.substring(20,40);
        charge = Integer.parseInt(obj.substring(40,60).trim());
        notify=Integer.parseInt(obj.substring(60,80).trim());
        return this;
    }
    public void updateCharge(int chargeAdded) {
        charge += chargeAdded;
    }
    public void addNotify() {
        notify += 1 ;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }
    public String getPassword() {
        return password;
    }

    public int getCharge() {
        return charge;
    }

    public int getNotify() {
        return notify;
    }
    public void setPassword(String password) {
        this.password = password;
    }



}
