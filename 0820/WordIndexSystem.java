import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordIndexSystem {

    public static void main(String[] args) {
        String[] sentences = {
            "Java is a popular programming language.",
            "Data structures and algorithms are fundamental in Java programming.",
            "Practice makes perfect, so practice Java daily!"
        };

        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        Set<String> uniqueWordsSet = new HashSet<>();

        for (String sentence : sentences) {
            String cleanedSentence = sentence.toLowerCase().replaceAll("[,.]", "");
            String[] words = cleanedSentence.split("\\s+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    uniqueWordsSet.add(word);
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        List<String> frequentWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue() >= 2) {
                frequentWords.add(entry.getKey());
            }
        }

        System.out.println("=== 所有不重複單字 (Set, 總數: " + uniqueWordsSet.size() + ") ===");
        System.out.println(uniqueWordsSet);

        System.out.println("\n=== 單字出現頻率統計 (Map) ===");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " 次");
        }

        System.out.println("\n=== 出現至少兩次的單字 (Count >= 2) ===");
        for (String word : frequentWords) {
            System.out.println(word + " -> " + wordFrequencyMap.get(word) + " 次");
        }
    }
}