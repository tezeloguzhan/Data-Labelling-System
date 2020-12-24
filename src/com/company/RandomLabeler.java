package com.company;

import java.util.Random;
import java.util.Scanner;
public class RandomLabeler extends DataLabeler{


    public RandomLabeler(DataSet datasetInstance, com.company.User[] user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {
        System.out.println("Data Labeler Type: Random Labeler");
        DataInstance instanceList [] = this.datasetInstance.getInstance();

        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        for(int i=0; i<this.user.length; i++){
            System.out.printf("%d: %s\n",this.user[i].getId(), this.user[i].getUserName());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Select User using the shown ID: ");
        int userID = scan.nextInt();
        com.company.User currUser = new com.company.User();
        for(int i=0; i<this.user.length; i++){
            if(user[i].getId() == userID){
                currUser = user[i];
                break;
            }
        }
        System.out.printf("Current User: ");
        System.out.println(currUser.getJsonObject().toJSONString());
        for(int i=0; i < instanceList.length; i++){
            DataInstance dataInst = instanceList[i];
            int rnd = new Random().nextInt(labelList.length);
            ClassLabel cl;
            cl = labelList[rnd];
            com.company.User user = currUser;
            DataLabel dataLabel = new DataLabel(dataInst, user);
            dataLabel.addClassLabel(cl);
            this.dataLabelList[i] = dataLabel;
        }




    }
}
