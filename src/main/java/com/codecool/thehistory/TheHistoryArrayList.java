package com.codecool.thehistory;

import java.util.*;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        String[] wordArray = text.split("\\s+");
        Collections.addAll(wordsArrayList, wordArray);
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        for (int i = 0; i < size(); i++) {
            if (wordsArrayList.get(i).equals(wordToBeRemoved)) {
                wordsArrayList.remove(i);
            }
        }
    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList = new ArrayList<>();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < size(); i++) {
            if (wordsArrayList.get(i).equals(from)) {
                wordsArrayList.set(i, to);
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int lenFrom = fromWords.length;
        int lenTo = toWords.length;

        if (lenFrom == lenTo) {
            for (int i = 0; i < size(); i++) {
                if (wordsArrayList.get(i).equals(fromWords[0])) {
                    int pos = 0;
                    int cnt = 0;
                    for (int j = i; ((j < size()) && (j < i + lenFrom)); j++) {
                        if (wordsArrayList.get(j).equals(fromWords[pos++])) {
                            cnt++;
                        } else {
                            break;
                        }
                    }
                    if (cnt == lenFrom) {
                        pos = 0;
                        for (int j = i; ((j < size()) && (j < i + lenFrom)); j++) {
                            wordsArrayList.set(j, toWords[pos++]);
                        }
                    }
                }
            }
        } else if (lenFrom < lenTo) {
            for (int i = 0; i < size(); ) {
                if (wordsArrayList.get(i).equals(fromWords[0])) {
                    int pos = 0;
                    int cnt = 0;
                    for (int j = i; (j < i + lenFrom) && (j < size()); j++) {
                        if (wordsArrayList.get(j).equals(fromWords[pos++])) {
                            cnt += 1;
                        } else {
                            break;
                        }
                    }
                    if (cnt == lenFrom) {
                        pos = 0;
                        int j;
                        for (j = i; (j < i + lenFrom) && (j < size()); j++) {
                            wordsArrayList.set(j, toWords[pos++]);
                        }

                        String[] wordsToInsert = Arrays.copyOfRange(toWords, lenFrom, lenTo);
                        insertWordsAtIndex(j, wordsToInsert);
                        i += lenTo;

                    } else {
                        i++;
                    }
                } else {
                    i++;
                }
            }
        } else {
            for (int i = 0; i < size(); i++) {
                if (wordsArrayList.get(i).equals(fromWords[0])) {
                    int pos = 0;
                    int cnt = 0;
                    for (int j = i; (j < i + lenFrom) && (j < size()); j++) {
                        if (wordsArrayList.get(j).equals(fromWords[pos++])) {
                            cnt++;
                        } else {
                            break;
                        }
                    }
                    if (cnt == lenFrom) {
                        pos = 0;
                        int j;
                        for (j = i; (j < i + lenTo) && (j < size()); j++) {
                            wordsArrayList.set(j, toWords[pos++]);
                        }

                        deleteWordsAtIndex(j, lenFrom - lenTo);
                    }
                }
            }
        }
    }

    private void insertWordsAtIndex(int index, String[] wordsToInsert) {
        int pos = index;
        for (String aWordsToInsert : wordsToInsert) {
            wordsArrayList.add(pos++, aWordsToInsert);
        }
    }

    private void deleteWordsAtIndex(int index, int amount) {
        for (int i = 0; i < amount; i++) {
            wordsArrayList.remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
