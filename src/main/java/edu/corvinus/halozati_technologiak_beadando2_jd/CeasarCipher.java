package edu.corvinus.halozati_technologiak_beadando2_jd;

public class CeasarCipher {

    public String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character;
                int newAlphabetPosition = originalAlphabetPosition + offset;
                char newCharacter = (char) (newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return String.valueOf(result);
    }

    public String decipher(String message, int offset) {
        return cipher(message, (0-offset));
    }


}

