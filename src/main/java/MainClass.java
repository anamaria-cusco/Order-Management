
import presentation.controller.AppController;
import presentation.controller.ClientControlller;
import presentation.IntroFrame;
import presentation.controller.OrderController;
import presentation.controller.ProductController;
import utils.FileWriterClass;



public class MainClass {
    /**
     * Created by Ana-Maria on 20.04.2020
     */
    public static void main(String[] args) {
       new AppController(new IntroFrame(), new ClientControlller(),new ProductController(),new OrderController());



    }
}
