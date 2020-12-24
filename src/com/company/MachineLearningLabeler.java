package com.company;

import java.util.Scanner;

public class MachineLearningLabeler extends DataLabeler{

    public MachineLearningLabeler(DataSet datasetInstance, User[] user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {

        System.out.println("Data Labeler Type: Custom Labeler");
        DataInstance instanceList [] = this.datasetInstance.getInstance();
        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        int exit = 0;
        int dataLabelIndex = 0;
        User currUser = CurrentUser();
        while(true) {
            DataInstance[] userLabeled = getUserLabeledData(currUser);
            if (userLabeled != null) {
                System.out.println("Instances Labeled by current User:");
                for (DataInstance ul : userLabeled) {
                    if(ul!=null) {
                        System.out.println(ul.getJsonObject().toJSONString());
                    }
                }
            } else {
                System.out.println("No Instances Labeled by current User:");
            }

            System.out.println("Available Instances:");
            for (DataInstance di : instanceList) {
                if(di!=null) {
                    System.out.println(di.getJsonObject().toJSONString());
                }
            }
            System.out.println("Select Instance Using ID: ");
            Scanner scan = new Scanner(System.in);
            int instId = scan.nextInt();
            DataInstance currInstance = getCurrentDataInstance(instId);
            System.out.println();
            System.out.printf("Current Instance: %s", currInstance.getJsonObject().toJSONString());

            System.out.println("Which Class Label you want to assign to the Instance?");
            for (ClassLabel cl : labelList){
                if(cl!=null) {
                    System.out.println(cl.getJsonObject());
                }
            }
            System.out.println("Select Class Label Using ID: ");
            int classLabelId = scan.nextInt();
            ClassLabel currLabel = getCurrentLabel(classLabelId);

            DataLabel currDataLabel = getDataLabel(currInstance);
            if (currDataLabel == null){
                currDataLabel = new DataLabel();
                currDataLabel.setUser(currUser);
                currDataLabel.setInstance(currInstance);
                currDataLabel.addClassLabel(currLabel);
                this.dataLabelList[dataLabelIndex] = currDataLabel;
                dataLabelIndex ++;
            }
            else{
                currDataLabel.addClassLabel(currLabel);
            }

            System.out.println("Instance Labeled");
            System.out.println(currDataLabel.getJsonObject().toJSONString());

            System.out.println("\nDo you want to ");
            System.out.println("1: Label Another Instance");
            System.out.println("2: Change User");
            System.out.println("3: Exit");
            int opt = scan.nextInt();
            switch (opt){
                case 1:
                    break;
                case 2:
                    currUser = CurrentUser();
                    break;
                case 3:
                    exit = 1;
                    break;
            }
            if (exit==1){
                break;
            }



            System.out.println("-----------------------------------------------");
        }



    }

    public DataLabel getDataLabel(DataInstance dataInst){
        if (dataLabelList[0]!=null) {
            for (DataLabel dl : this.dataLabelList) {
                if(dl!=null) {
                    if (dl.getInstance().getId() == dataInst.getId()) {
                        return dl;
                    }
                }
            }
        }

        return null;

    }

    public ClassLabel getCurrentLabel(int labelId){
        for (ClassLabel cl : this.datasetInstance.getClassLabel()){
            if (cl.getId() == labelId){
                return cl;
            }
        }

        return null;
    }

    public DataInstance getCurrentDataInstance(int instId){
        for(DataInstance di : this.datasetInstance.getInstance()){
            if(di.getId() == instId){
                return di;
            }

        }
        return null;
    }

    public DataInstance[] getUserLabeledData(User user){
        DataInstance [] instanceList = new DataInstance[this.dataLabelList.length];
        if (dataLabelList.length > 0 && dataLabelList[0] != null) {
            int j = 0;
            for (int i = 0; i < this.dataLabelList.length; i++) {
                if(this.dataLabelList[i]!=null) {
                    if (this.dataLabelList[i].getUser().getId() == user.getId()) {
                        instanceList[j] = dataLabelList[i].getInstance();
                        j++;
                    }
                }
            }
        }
        if (instanceList[0] != null){
            return instanceList;
        }
        else{
            return null;
        }

    }

    public User CurrentUser(){
        for(int i=0; i<this.user.length; i++){
            System.out.printf("%d: %s\n",this.user[i].getId(), this.user[i].getUserName());
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Select User using the shown ID: ");
        int userID = scan.nextInt();
        User currUser = new User();
        for(int i=0; i<this.user.length; i++){
            if(user[i].getId() == userID){
                currUser = user[i];
                break;
            }
        }
        System.out.printf("Current User: ");
        System.out.println(currUser.getJsonObject().toJSONString());
        return currUser;
    }

}