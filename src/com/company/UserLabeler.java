package com.company;

import java.util.Scanner;

public class UserLabeler extends DataLabeler{
    public UserLabeler(DataSet datasetInstance, User user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {
        System.out.println("\n-------------User Labeling System----------");
        DataInstance instanceList [] = this.datasetInstance.getInstance();
        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        DataInstance[] labeledInstances = new DataInstance[instanceList.length];
        int exit = 0;
        int dataLabelIndex = 0;

        User currUser = this.user;
        UserLog userLog = new UserLog();

        DataInstance[] userLabeled = userLog.getUserLog(currUser);
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

//        System.out.println("Available Instances:");
        Scanner scan = new Scanner(System.in);
        int li = 0;
        for (DataInstance di : instanceList) {
            if(di!=null && alreadyLabeled(di, userLabeled) == false) {
                DataInstance currInstance = di;
//                System.out.println(di.getJsonObject().toJSONString());
                System.out.printf("\n\nCurrent Instance: %s", currInstance.getJsonObject().toJSONString());
                System.out.println("\n\nWhich Class Label you want to assign to the Instance?");
                for (ClassLabel cl : labelList){
                    if(cl!=null) {
                        System.out.println(cl.getJsonObject());
                    }
                }
                System.out.print("Select Class Label Using ID: ");
                int classLabelId = scan.nextInt();
                ClassLabel currLabel = getCurrentLabel(classLabelId);

                DataLabel currDataLabel = getDataLabel(currInstance);
                if (currDataLabel == null){
                    currDataLabel = new DataLabel();
                    currDataLabel.setUser(this.user);
                    currDataLabel.setInstance(currInstance);
                    currDataLabel.addClassLabel(currLabel);
                    this.dataLabelList[dataLabelIndex] = currDataLabel;
                    dataLabelIndex ++;
                }
                else{
                    currDataLabel.addClassLabel(currLabel);
                }

                System.out.println("\nInstance Labeled");
                System.out.println(currDataLabel.getJsonObject().toJSONString());
                labeledInstances[li] = currInstance;
                li++;
                userLog.createUserLog(labeledInstances, currUser);


            }
        }


    }


    public boolean alreadyLabeled(DataInstance currInst, DataInstance [] userLabeled){
        boolean labeled = false;
        if (userLabeled!=null) {
            for (DataInstance instance : userLabeled) {
                if (instance != null) {
                    if (currInst.getId() == instance.getId()) {
                        labeled = true;
                        break;
                    }
                }
            }
        }
        return labeled;
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

}
