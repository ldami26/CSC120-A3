import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Conversation {

    private int rounds;  // Number of conversation rounds
    private ArrayList<String> transcript;  // Stores the conversation transcript
    private Scanner scanner;

    // Constructor that takes the number of rounds
    public Conversation(int rounds) {
        this.rounds = rounds;
        this.transcript = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Generates the responses with pronoun mirroring
    private String generateResponse(String input) {
        // Random responses without the mirroring
        String[] cannedResponses = {
            "Interesting. Tell me more.",
            "Mmm-hm.",
            "Really? That's fascinating!",
            "Could you explain?",
            "Go on, I'm listening."
        };

        // Split user input into words
        String[] words = input.split(" ");
        StringBuilder mirroredResponse = new StringBuilder();

        // Pronoun mirroring pairs
        String[][] mirrors = {
            {"I", "you"},
            {"me", "you"},
            {"am", "are"},
            {"you", "I"},
            {"my", "your"},
            {"your", "my"},
            {".", "?"}
        };

        // Apply mirroring to the user input
        for (String word : words) {
            boolean mirrored = false;
            for (String[] pair : mirrors) {
                if (word.equalsIgnoreCase(pair[0])) {
                    mirroredResponse.append(pair[1]).append(" ");
                    mirrored = true;
                    break;
                } else if (word.equalsIgnoreCase(pair[1])) {
                    mirroredResponse.append(pair[0]).append(" ");
                    mirrored = true;
                    break;
                }
            }
            if (!mirrored) {
                mirroredResponse.append(word).append(" ");
            }
        }

        // If no mirroring happened, return a canned response
        if (mirroredResponse.toString().trim().equals(input.trim())) {
            Random random = new Random();
            return cannedResponses[random.nextInt(cannedResponses.length)];
        }

        // Return the mirrored response
        return mirroredResponse.toString().trim() + "?";
    }

    // Start the conversation
    public void start() {
        System.out.println("Hi there! What's on your mind?");
        transcript.add("Hi there! What's on your mind?");

        // Loop for the specified number of rounds
        for (int i = 0; i < rounds; i++) {
            String userInput = scanner.nextLine();
            transcript.add("User: " + userInput);

            String response = generateResponse(userInput);
            System.out.println(response);
            transcript.add("Bot: " + response);
        }

        // Say goodbye at the end
        System.out.println("See ya!");
        transcript.add("Bot: See ya!");

        // Print the transcript of the conversation
        printTranscript();
    }

    // Prints the conversation transcript
    private void printTranscript() {
        System.out.println("\nTRANSCRIPT:");
        for (String line : transcript) {
            System.out.println(line);
        }
    }

    // Main method to be called from Main.java
    public static void main(String[] args) {
        // Ask the user how many rounds they want to go
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many rounds? ");
        int rounds = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        // Create a Conversation object and start the conversation
        Conversation conversation = new Conversation(rounds);
        conversation.start();
    }
}