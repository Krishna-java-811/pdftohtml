package NER.name_entity;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.ArrayList;
import java.util.List;

public class StanfordNERExample {

    private static final String MODEL_PATH = "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.all.3class.distsim.crf.ser.gz";

    public static void main(String[] args) {
        String text = "Red Bull Racing Honda, the four-time Formula-1 World Champion team, has chosen Oracle Cloud Infrastructure (OCI) as their infrastructure partner.";

        List<NerEntity> entities = extractEntities(text);

        for (NerEntity entity : entities) {
            System.out.println(entity);
        }
    }

    private static List<NerEntity> extractEntities(String text) {
        List<NerEntity> entities = new ArrayList<>();

        try {
            CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(MODEL_PATH);

            List<List<CoreLabel>> out = classifier.classify(text);

            for (List<CoreLabel> sentence : out) {
                for (CoreLabel word : sentence) {
                    String entityText = word.get(CoreAnnotations.TextAnnotation.class);
                    String entityType = word.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                    System.out.println("Text: " + entityText + ", Entity Type: " + entityType);

                    if (entityType != null && !entityType.equals("O")) {
                        int start = word.beginPosition();
                        int end = word.endPosition();

                        entities.add(new NerEntity(start, end - start, entityText, entityType, 1.0)); // Score is 1.0 for simplicity
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entities;
    }

    // Define a class to hold NER entity information
    private static class NerEntity {
        private int offset;
        private int length;
        private String text;
        private String type;
        private double score;

        public NerEntity(int offset, int length, String text, String type, double score) {
            this.offset = offset;
            this.length = length;
            this.text = text;
            this.type = type;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "text='" + text + '\'' +
                    ", type='" + type + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
