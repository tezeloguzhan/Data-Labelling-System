package com.company;

import java.util.Random;
import java.util.Scanner;
public class RandomLabeler extends DataLabeler{


    public RandomLabeler(DataSet datasetInstance, User user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {

        System.out.println("-----------Random Labeler-----------");
        DataInstance instanceList [] = this.datasetInstance.getInstance();

        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        User currUser = this.user;
        System.out.printf("Current User: ");
        System.out.println(currUser.getJsonObject().toJSONString());
        for(int i=0; i < instanceList.length; i++){
            DataInstance dataInst = instanceList[i];
            int rnd = new Random().nextInt(labelList.length);
            ClassLabel cl;
            cl = labelList[rnd];
            User user = currUser;
            DataLabel dataLabel = new DataLabel(dataInst, user);
            dataLabel.addClassLabel(cl);
            this.dataLabelList[i] = dataLabel;
            System.out.println(dataLabel.getJsonObject().toJSONString());
        }

    }
}
