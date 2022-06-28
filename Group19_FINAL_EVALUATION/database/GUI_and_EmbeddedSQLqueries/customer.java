package application;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;


import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Customer 
{
        
        long userid;
        String username;
        Scene scene;
        ScrollPane srlp;
        static Text name;
        static VBox root2;        
        Scanner sc = new Scanner(System.in);
        Customer(long id , String name)
        {
                this.userid = id;
                this.username = name;
                this.srlp = new ScrollPane();
                this.scene =  new Scene(srlp,700,700);
                Main.stage.setScene(scene);
                setup();        
        }
        
        private void setup() 
        {
                root2 = new VBox();
                srlp.setContent(root2);
                Pane root = new Pane();
                root.setMaxWidth(700);
                root.setMinWidth(700);
                root.setMinHeight(300);
                root.setStyle("-fx-border-width: 2;-fx-border-color: gray; -fx-border-style: solid;");
                root2.getChildren().add(root);
                Button profile  = new Button("Profile");
                root.getChildren().add(profile);
                profile.setOnAction(e->showprofile());
                
                Button cart = new Button("Cart");
                cart.setTranslateX(600);
                cart.setTranslateY(30);
                root.getChildren().add(cart);                
                cart.setOnAction(e->opencart());
                
                
                name = new Text("Hi "+username);
                root.getChildren().add(name);
                name.setScaleX(1.8);
                name.setScaleY(1.8);
                name.setTranslateX(300);
                name.setTranslateY(30);
        
                
                TextArea search = new TextArea();
                search.setMaxHeight(20);
                search.setMaxWidth(100);
                root.getChildren().add(search);
                search.setTranslateX(300);
                search.setTranslateY(100);
                
                Button srh = new Button("Search");
                root.getChildren().add(srh);
                srh.setTranslateX(400);
                srh.setTranslateY(100);
                srh.setOnAction(e->search(search.getText()));
                
                Button srhcat = new Button("Search category");
                root.getChildren().add(srhcat);
                srhcat.setTranslateX(400);
                srhcat.setTranslateY(150);
                srhcat.setOnAction(e->opencategory(search.getText()));
                
                Button cats = new Button("Show all categories");
                root.getChildren().add(cats);
                cats.setTranslateX(400);
                cats.setTranslateY(200);
                cats.setOnAction(e->showcats(search.getText()));
                
        }
        private void showcats(String text)
        {
                try {
                        ResultSet result = querries.displayallcategories();
                        ResultSetMetaData rsmd = result.getMetaData();
                        Pane p = (Pane)root2.getChildren().get(0);
                        root2.getChildren().clear();
                        root2.getChildren().add(p);
                        while(result.next())
                        {
                                String u = "";
                                Button temp = new Button();
                                for(int i=1;i<=rsmd.getColumnCount();i++)
                                {                u+=result.getString(i);         }
                                temp.setText(u);
                                temp.setOnAction(e->{opencategory(temp.getText());});        
                                root2.getChildren().add(temp);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                
        }
        private void opencategory(String string) 
        {
                System.out.println("opening cat->"+string);
                ResultSet result = querries.searchcat(string);
                Pane p = (Pane)root2.getChildren().get(0);
                root2.getChildren().clear();
                root2.getChildren().add(p);
                try {
                        ResultSetMetaData rsmd = result.getMetaData();
                        while(result.next())
                        {
                                String u = "";
                                Button temp = new Button();
                                //for(int i=1;i<=rsmd.getColumnCount();i++)
                                {                u+=result.getString(1);         }
                                temp.setText(u);
                                temp.setOnAction(e->{openproduct(temp.getText());});        
                                root2.getChildren().add(temp);
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }
        private void openproduct(String text) 
        {
                try {
                        
                Pane root = new Pane();
                Main.stage.getScene().setRoot(root);
                ResultSet result = querries.getproductdetails(text);
                result.next();
                ResultSetMetaData rsmd = result.getMetaData();
                
                int id = Integer.parseInt(result.getString(2));
                
                
                Text name = new Text("Product Name -> " + text );
                Text idtxt = new Text("Product id -> " + id );
                Text price = new Text("Price -> " + result.getString(3) );
                
                name.setTranslateX(100);
                name.setTranslateY(100);
                idtxt.setTranslateX(100);
                idtxt.setTranslateY(150);
                price.setTranslateX(100);
                price.setTranslateY(200);
                
                
                root.getChildren().addAll(name,price,idtxt);
                
                
                Button back = new Button("Back");
                root.getChildren().add(back);
                back.setTranslateX(10);
                back.setTranslateY(10);
                back.setOnAction(e->
                {
                        Main.stage.getScene().setRoot(this.srlp);
                });
                
                
                Button order = new Button("Buy Now");
                root.getChildren().add(order);
                order.setTranslateX(100);
                order.setTranslateY(600);
                order.setOnAction(e->order(id));
                
                
                Button add = new Button("Add to cart");
                root.getChildren().add(add);
                add.setTranslateX(530);
                add.setTranslateY(600);
                add.setOnAction(e->{
                        try {
                                addtocart(id);
                        } catch (SQLException e1) {        e1.printStackTrace();}
                });
                
                } catch (SQLException e1) {        e1.printStackTrace();        }
                
                
        }
        private void addtocart(int pid) throws SQLException 
        {
                int cid = querries.getcartid(userid);
                if(cid == 0)
                {
                        cid = querries.newcart(userid);
                }
                if(querries.checkcart(pid, cid))
                {
                        querries.updatequantity(cid, pid, 1);
                }
                else
                {
                        querries.addtocart(cid,pid,1);                                                
                }
                
        }


        private Object order(int id) 
        {
                
                Pane root = new Pane();
                scene.setRoot(root);
                
                Button onp = new Button("Online Payment");
                root.getChildren().add(onp);
                onp.setTranslateX(200);
                onp.setTranslateY(500);
                onp.setOnAction(e->{
                        
                        
                });
                
                Button cashond = new Button("cash on delivery");
                root.getChildren().add(cashond);
                cashond.setTranslateX(500);
                cashond.setTranslateY(500);
                cashond.setOnAction(e->{
                        
                        
                });
                
                
                Button back = new Button("Main menue");
                root.getChildren().add(back);
                back.setTranslateX(10);
                back.setTranslateY(10);
                back.setOnAction(e->{
                        scene.setRoot(srlp);
                });
                
                
                return null;
        }
        
        public static void payproced()
        {
                
        }


        private void search(String text) 
        {
                try {
                        Pane p = (Pane)root2.getChildren().get(0);
                        root2.getChildren().clear();
                        root2.getChildren().add(p);
                        ResultSet result = querries.search(text);
        
                        ResultSetMetaData rsmd = result.getMetaData();
                        while(result.next())
                        {
                                String u = "";
                                Button temp = new Button();
                                        for(int i=1;i<=rsmd.getColumnCount();i++)
                                        {
                                                u+=result.getString(i)+" :"; 
                                        }
                                temp.setText(u);
                                root2.getChildren().add(temp);
                                //System.out.println(u);
                        }
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
        }


        private void opencart() 
        {
                
                
        }


        private void showprofile() 
        {
                try {                
                Pane root = new Pane();
                Main.stage.getScene().setRoot(root);
                ResultSet result = querries.getprofiledata(userid);
                result.next();
                ResultSetMetaData rsmd = result.getMetaData();
                
                String email = result.getString(3);
                String address = result.getString(4);
                String dob = result.getString(6);
                
                
                
                Text name = new Text("Name -> " + username );
                Text idtxt = new Text("User id -> " + userid );
                Text mail = new Text("Email -> " + email );
                Text dobtxt = new Text("DOB -> " + dob );
                Text addres = new Text("Address -> " + address );
                
                idtxt.setTranslateX(100);
                idtxt.setTranslateY(100);
                name.setTranslateX(100);
                name.setTranslateY(150);
                mail.setTranslateX(100);
                mail.setTranslateY(200);
                dobtxt.setTranslateX(100);
                dobtxt.setTranslateY(250);
                addres.setTranslateX(100);
                addres.setTranslateY(300);
                
                
                root.getChildren().addAll(name,mail,idtxt,dobtxt,addres);
                
                
                Button back = new Button("Back");
                root.getChildren().add(back);
                back.setTranslateX(10);
                back.setTranslateY(10);
                back.setOnAction(e->
                {
                        Main.stage.getScene().setRoot(this.srlp);
                });
                
                
                Button changemail = new Button("Change E-Mail");
                changemail.setTranslateX(550);
                changemail.setTranslateY(200-10);
                root.getChildren().add(changemail);
                changemail.setOnAction(e->{
                        Button temp = new Button("Change");
                        TextField input = change(changemail,root,temp);
                        temp.setOnAction(e2->
                        {
                                String str  = input.getText();
                                System.out.println("trying to chnage to "+str);
                                if(querries.changemail(userid, str))
                                {
                                        mail.setText("E-mail -> " + str);
                                }
                                root.getChildren().remove(temp);
                                root.getChildren().remove(input);
                                root.getChildren().add(changemail);
                                changemail.setTranslateX(550);
                                changemail.setTranslateY(200-10);
                        });
                });
                
                
                Button changeadd = new Button("Change Address");
                root.getChildren().add(changeadd);
                changeadd.setTranslateX(550);
                changeadd.setTranslateY(addres.getTranslateY()-10);
                changeadd.setOnAction(e->
                {
                        Button temp = new Button("Change");
                        TextField input = change(changeadd,root,temp);
                        temp.setOnAction(e2->
                        {
                                String str  = input.getText();
                                System.out.println("trying to chnage to "+str);
                                if(querries.changeaddres(userid, str))
                                {
                                        mail.setText("Address -> " + str);
                                }
                                root.getChildren().remove(temp);
                                root.getChildren().remove(input);
                                root.getChildren().add(changeadd);
                                changeadd.setTranslateX(550);
                                changeadd.setTranslateY(addres.getTranslateY()-10);
                        });
                        
                });
                
                Button changedob = new Button("Change DOB");
                root.getChildren().add(changedob);
                changedob.setTranslateX(550);
                changedob.setTranslateY(dobtxt.getTranslateY()-10);
                changedob.setOnAction(e->
                {
                        Button temp = new Button("Change");
                        TextField input = change(changedob,root,temp);
                        temp.setOnAction(e2->
                        {
                                String str  = input.getText();
                                System.out.println("trying to chnage to "+str);
                                if(querries.changedob(userid, str))
                                {
                                        dobtxt.setText("DOB -> " + str);
                                }
                                root.getChildren().remove(temp);
                                root.getChildren().remove(input);
                                root.getChildren().add(changedob);
                                changedob.setTranslateX(550);
                                changedob.setTranslateY(dobtxt.getTranslateY()-10);
                        });
                        
                });
                
                
                Button changename = new Button("Change Name");
                root.getChildren().add(changename);
                changename.setTranslateX(550);
                changename.setTranslateY(name.getTranslateY()-10);
                changename.setOnAction(e->
                {
                        Button temp = new Button("Change");
                        TextField input = change(changename,root,temp);
                        temp.setOnAction(e2->
                        {
                                String str  = input.getText();
                                System.out.println("trying to chnage to "+str);
                                if(querries.changename(userid, str))
                                {
                                        name.setText("Name -> " + str);
                                        Customer.name.setText(str);
                                }
                                root.getChildren().remove(temp);
                                root.getChildren().remove(input);
                                root.getChildren().add(changename);
                                changename.setTranslateX(550);
                                changename.setTranslateY(addres.getTranslateY()-10);
                        });
                        
                });
                
        }catch (SQLException e1) {        e1.printStackTrace();}
                
        }
        
        public static TextField change(Button b, Pane root ,Button temp)
        {


                TextField input = new TextField("Enter Here");
                root.getChildren().add(input);
                input.setTranslateX(b.getTranslateX()-100);
                input.setTranslateY(b.getTranslateY());
                root.getChildren().add(temp);
                temp.setTranslateX(b.getTranslateX());
                temp.setTranslateY(b.getTranslateY());
                root.getChildren().remove(b);
                return input;
                
        }
        
        
        
        


        public static void main(String[] args) 
        {
                


        }


}