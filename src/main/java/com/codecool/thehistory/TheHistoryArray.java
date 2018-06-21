package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] temp = text.split("\\s+");

        int newLength = size() + temp.length;
        String[] newWordsArray = new String[newLength];
        int pos;

        // Copy the existing words
        for (pos = 0; pos < size(); pos++) {
            newWordsArray[pos] = wordsArray[pos];
        }

        // Adding the new words
        for (; pos < newLength; pos++) {
            newWordsArray[pos] = temp[pos];
        }

        wordsArray = newWordsArray;
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        int count = 0;
        for (String word : wordsArray) {
            if (word.equals(wordToBeRemoved)) {
                count++;
            }
        }

        String[] newWordsArray = new String[size() - count];
        int pos = 0;

        for (String word : wordsArray) {
            if (!(word.equals(wordToBeRemoved))) {
                newWordsArray[pos++] = word;
            }
        }

        wordsArray = newWordsArray;
    }

    @Override
    public int size() {
        return wordsArray.length;
    }

    @Override
    public void clear() {
        wordsArray = new String[0];
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i = 0; i < size(); i++) {
            if (wordsArray[i].equals(from)) {
                wordsArray[i] = to;
            }
        }
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        int lenFrom = fromWords.length;
        int lenTo = toWords.length;

        if (lenFrom == lenTo) {
            for (int i = 0; i < size(); i++) {
                if (wordsArray[i].equals(fromWords[0])) {
                    int pos = 0;
                    boolean isEqual = true;
                    for (int j = i; (j < i + lenFrom) && (j < size()); j++) {
                        if (!wordsArray[j].equals(fromWords[pos++])) {
                            isEqual = false;
                            break;
                        }
                    }
                    if (isEqual) {
                        pos = 0;
                        int j;
                        for (j = i; (j < i + lenFrom) && (j < size()); j++) {
                            wordsArray[j] = toWords[pos++];
                        }
                    }
                }
            }
        } else if (lenFrom < lenTo) {
            for (int i = 0; i < size(); ) {
                if (wordsArray[i].equals(fromWords[0])) {
                    int pos = 0;
                    int cnt = 0;
                    for (int j = i; (j < i + lenFrom) && (j < size()); j++) {
                        if (wordsArray[j].equals(fromWords[pos++])) {
                            cnt += 1;
                        } else {
                            break;
                        }
                    }
                    if (cnt == lenFrom) {
                        pos = 0;
                        int j;
                        for (j = i; (j < i + lenFrom) && (j < size()); j++) {
                            wordsArray[j] = toWords[pos++];
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
                if (wordsArray[i].equals(fromWords[0])) {
                    int pos = 0;
                    int cnt = 0;
                    for (int j = i; (j < i + lenFrom) && (j < size()); j++) {
                        if (wordsArray[j].equals(fromWords[pos++])) {
                            cnt++;
                        } else {
                            break;
                        }
                    }
                    if (cnt == lenFrom) {
                        pos = 0;
                        int j;
                        for (j = i; (j < i + lenTo) && (j < size()); j++) {
                            wordsArray[j] = toWords[pos++];
                        }

                        deleteWordsAtIndex(j, lenFrom - lenTo);
                    }
                }
            }
        }
    }

    private void insertWordsAtIndex(int index, String[] wordsToInsert) {
        int numberOfInsertions = wordsToInsert.length;

        String[] newWordsArray = new String[size() + numberOfInsertions];

        System.arraycopy(wordsArray, 0, newWordsArray, 0, index);
        System.arraycopy(wordsToInsert, 0, newWordsArray, index, numberOfInsertions);
        System.arraycopy(wordsArray, index, newWordsArray, index + numberOfInsertions, size() - index);

        wordsArray = newWordsArray;
    }

    private void deleteWordsAtIndex(int index, int amount) {
        String[] newWordsArray = new String[size() - amount];

        System.arraycopy(wordsArray, 0, newWordsArray, 0, index);
        System.arraycopy(wordsArray, index + amount, newWordsArray, index, size() - amount - index);

        wordsArray = newWordsArray;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }
}
