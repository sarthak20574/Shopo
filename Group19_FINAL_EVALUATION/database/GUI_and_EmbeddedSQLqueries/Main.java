package application;
        
import java.sql.SQLException;
import java.util.Scanner;




import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Main extends Application 
{
        
        static Stage stage;
        static Pane root;
        static Scene scene; 
        
        public static void main(String[] args) throws SQLException 
        {
                querries.connect(null);
                launch(args);


        }
        public void start(Stage stage) 
        {
                this.root = new Pane();
                Scene scene = new Scene(root,700,700);
                loginpage();
                this.stage = stage;
                this.scene = scene;
                stage.setTitle("DBMS PROJECT");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();        
        }
                
        public void loginpage() 
        {                
                Button customer = new Button("Login as Customer");
                root.getChildren().add(customer);
                customer.setOnAction(e->verifycustomer());
                customer.setTranslateX(300);
                customer.setTranslateY(200);        


        //        verifycustomer();
//                
//                Button adm = new Button("Login as Administrator");
//                root.getChildren().add(adm);
//                adm.setOnAction(e->verifyadm());
//                adm.setTranslateX(300);
//                adm.setTranslateY(400);
//                
                
//                Button supplir = new Button("Login as Supplier");
//                root.getChildren().add(supplir);
//                //supplir.setOnAction(e->verifyadm());
//                supplir.setTranslateX(300);
//                supplir.setTranslateY(600);
                
        
        }        
        private void verifyadm() 
        {


                Pane root = new Pane();
                scene.setRoot(root);
                TextField uid = new TextField("USERNAME");
                TextField pass = new TextField("PASSWORD");
                uid.setMaxWidth(100);
                uid.setMinWidth(100);
                pass.setMaxWidth(100);
                pass.setMinWidth(100);
                
                
                uid.setTranslateX(200);
                uid.setTranslateY(200);
                
                pass.setTranslateX(200);
                pass.setTranslateY(250);
                
                Button b = new Button("Login");
                b.setTranslateX(250);
                b.setTranslateY(300);
                
                root.getChildren().addAll(uid,pass,b);
                b.setOnAction(e->{
                        
                        adms.setup(scene);
                        
                });
                
                
        }
        private boolean verifycustomer() 
        {
                Pane root = new Pane();
                scene.setRoot(root);
                TextField uid = new TextField("USERNAME");
                TextField pass = new TextField("PASSWORD");
                uid.setMaxWidth(100);
                uid.setMinWidth(100);
                pass.setMaxWidth(100);
                pass.setMinWidth(100);
                
                
                uid.setTranslateX(300);
                uid.setTranslateY(300);
                
                pass.setTranslateX(300);
                pass.setTranslateY(350);
                
                Button b = new Button("Login");
                b.setTranslateX(350);
                b.setTranslateY(300);
                
                root.getChildren().addAll(uid,pass,b);
                b.setOnAction(e->{
                        
                        long id =  Integer.parseInt(uid.getText());
                        String name = querries.getname(id);
                        Customer cust = new Customer(id,name);
                        
                });
                
                return true;
        }
        
        
        
}














class adms 
{
        
        Scanner sc = new Scanner(System.in);
        Text t = new Text();
        
        
        public static  void setup(Scene scene) 
        {
                
                Pane root = new Pane();
                scene.setRoot(root);
                
                Button v1 = new Button("View 1");
                root.getChildren().add(v1);
                v1.setTranslateX(200);
                v1.setTranslateX(200);
                v1.setOnAction(e->{
                        String view1 = "";
                        querries.executequerry(view1);
                        
                });
                
                
                Button v2 = new Button("View 2");
                root.getChildren().add(v2);
                v2.setTranslateX(400);
                v2.setTranslateX(200);
                v2.setOnAction(e->{
                        String view1 = "";
                        querries.executequerry(view1);        
                                        
                });
                        
                        
                        
                Button run = new Button("Run");
                run.setTranslateX(500);
                run.setTranslateX(300);
                root.getChildren().add(run);
                
                
                TextField input = new TextField();
                root.getChildren().add(input);
                input.setTranslateX(100);
                input.setTranslateY(300);
                input.setMaxWidth(200);
                input.setMinWidth(200);
                
                run.setOnAction(e->{
                        
                });
                
                
                
        }


}