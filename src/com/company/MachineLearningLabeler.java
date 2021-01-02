package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MachineLearningLabeler extends DataLabeler {

    public MachineLearningLabeler(DataSet datasetInstance, User user) {
        super(datasetInstance, user);
    }

    @Override
    public void labelData() {
        System.out.println("\n-------------Machine Learning Labeling System----------");
        DataInstance instanceList [] = this.datasetInstance.getInstance();
        ClassLabel labelList [] = this.datasetInstance.getClassLabel();
        WordClass [] wordList = getWordList();
        DataInstance currInst;
        ClassLabel currLabel;
        System.out.println("Word Collection");
        for(WordClass wc : wordList){
            if (wc!=null) {
                System.out.println(wc);
            }
        }
        int i =0;
        for (DataInstance di : instanceList) {
            System.out.println(di.getJsonObject().toJSONString());
            currInst = di;
            currLabel = instanceClassification(wordList, di.getInstane(), labelList);
            DataLabel dataLabel = new DataLabel(currInst, this.user);
            dataLabel.addClassLabel(currLabel);
            this.dataLabelList[i] = dataLabel;
            i++;
        }
    }

    public ClassLabel instanceClassification(WordClass [] wordList, String instText, ClassLabel [] labelList){
        ClassLabel classLabel = labelList[0];
        for (WordClass wc: wordList){
            if(wc!=null) {
                if (instText.contains(wc.getWord())) {
                    return labelList[1];
                }
            }
        }
        return classLabel;
    }
    public WordClass [] getWordList(){
        WordClass wordClass [];
        String currPath = System.getProperty("user.dir") + "/MetaData/" + "wordsClassification.json";
        ReadJsonFile jsonReader = new ReadJsonFile();
        JSONObject jsonObject = jsonReader.readJson(currPath);
        JSONArray wordArray = (JSONArray) jsonObject.get("wordList");
        wordClass = new WordClass[wordArray.size()];
        int i =0;
        for (Object jb: wordArray){
            JSONObject jsonobj = (JSONObject) jb;
            WordClass wc = new WordClass((String) jsonobj.get("word"),(String) jsonobj.get("classification"));
            wordClass[i] = wc;
            i++;
        }

        return wordClass;
    }

}
