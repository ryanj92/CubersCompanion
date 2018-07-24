package io.github.ryanj92.cuberscompanion;

import java.util.ArrayList;

public class RandomMoveScrambler {

    String puzzleType;
    int scrambleLength;

    RandomMoveScrambler(String puzzleType) {
        this.puzzleType = puzzleType;
    }

    public String generateScramble(){

        StringBuilder scrambleBuilder = new StringBuilder();

        int prevMoveFace = 0;
        int prev2MoveFace = 0;
        int currentMoveFace = 0;
        int currentMoveDir;

        // determine scramble length
        switch (puzzleType) {
            case "333": scrambleLength = 25;
                        break;
            default: scrambleLength = 10;
                     break;
        }

        for (int i = 0; i < scrambleLength; i++) {

            // decide move face
            boolean validMove = false;

            while (!validMove) {
                currentMoveFace = (int) (Math.random() * 6) + 1;

                if (currentMoveFace != prevMoveFace) {

                    if (currentMoveFace == prev2MoveFace) {

                        if (currentMoveFace == 1 && prevMoveFace == 2
                                || currentMoveFace == 2 && prevMoveFace == 1
                                || currentMoveFace == 3 && prevMoveFace == 4
                                || currentMoveFace == 4 && prevMoveFace == 3
                                || currentMoveFace == 5 && prevMoveFace == 6
                                || currentMoveFace == 6 && prevMoveFace == 5) {
                            validMove = false;
                        } else {
                            validMove = true;
                        }

                    } else {
                        validMove = true;
                    }

                } else {
                    validMove = false;
                }
            }

            switch (currentMoveFace) {
                case 1: scrambleBuilder.append("R");
                        break;
                case 2: scrambleBuilder.append("L");
                        break;
                case 3: scrambleBuilder.append("U");
                        break;
                case 4: scrambleBuilder.append("D");
                        break;
                case 5: scrambleBuilder.append("F");
                        break;
                case 6: scrambleBuilder.append("B");
                        break;
                default: scrambleBuilder.append("X");
                         break;
            }

            // decide move direction
            currentMoveDir = (int) (Math.random()*3) + 1;

            switch (currentMoveDir) {
                case 1: break;
                case 2: scrambleBuilder.append("\'");
                        break;
                case 3: scrambleBuilder.append("2");
                        break;
                default: scrambleBuilder.append("Y");
                         break;
            }

            prevMoveFace = currentMoveFace;
            prev2MoveFace = prevMoveFace;

            if (i < scrambleLength - 1) scrambleBuilder.append(" ");

        }

        return scrambleBuilder.toString();
    }
}
