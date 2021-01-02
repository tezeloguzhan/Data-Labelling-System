package com.company;

public class PositionLabeler extends DataLabeler{

    public PositionLabeler(DataSet datasetInstance, User user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {
        System.out.println("-------------Position Labeler-------------\n");
        DataInstance instanceList [] = this.datasetInstance.getInstance();
        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        DataInstance currInst;
        ClassLabel currLabel;
        int ecount = 1;
        int ocount = 0;
        for(int i=0; i<instanceList.length; i++){
            System.out.print(instanceList[i].getJsonObject().toJSONString());
            if(i%2 == 0){   //if even number
                System.out.println("Index : Even");
                if (ecount >= labelList.length){
                    ecount = 1;
                }
                currLabel = labelList[labelList.length-1];
                currInst = instanceList[i];
                ecount--;
            }
            else{          //if odd number
                System.out.println("Index : Odd");
                if (ocount >= labelList.length){
                    ocount = 0;
                }
                currLabel = labelList[ocount];
                currInst = instanceList[i];
                ocount++;
            }
            DataLabel dataLabel = new DataLabel(currInst, this.user);
            dataLabel.addClassLabel(currLabel);
            this.dataLabelList[i] = dataLabel;

        }
    }
}
