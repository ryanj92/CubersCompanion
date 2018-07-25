package io.github.ryanj92.cuberscompanion;

public class RandomMoveScrambler {

    String puzzleType;

    String[] faces222 = new String[]{"U","R","F"};
    String[] faces333 = new String[]{"U","D","L","R","F","B"};
    String[] faces444_555 = new String[]{"U","u","D","d","L","l","R","r","F","f","B","b"};
    String[] faces666_777 = new String[]{"U","u","3u","D","d","3d",
            "L","l","3l","R","r","3r",
            "F","f","3f","B","b","3b"};
    int turnableLayersPerAxis;

    String[] dirsCubic = new String[]{"","\'","2"};

    String[] facesError = new String[]{"Error: Invalid puzzle type"};

    RandomMoveScrambler(String puzzleType) {
        this.puzzleType = puzzleType;
    }

    public boolean validMoveCheck(String checkMove, String[] moveQueue) {

        for (String move : moveQueue) {

            if (move == null) return true;
            if (checkMove.equals(move)) return false;

            if (!(checkMove.toLowerCase().contains("u") && move.toLowerCase().contains("u") ||
                    checkMove.toLowerCase().contains("u") && move.toLowerCase().contains("d") ||
                    checkMove.toLowerCase().contains("d") && move.toLowerCase().contains("d") ||
                    checkMove.toLowerCase().contains("d") && move.toLowerCase().contains("u") ||
                    checkMove.toLowerCase().contains("l") && move.toLowerCase().contains("l") ||
                    checkMove.toLowerCase().contains("l") && move.toLowerCase().contains("r") ||
                    checkMove.toLowerCase().contains("r") && move.toLowerCase().contains("r") ||
                    checkMove.toLowerCase().contains("r") && move.toLowerCase().contains("l") ||
                    checkMove.toLowerCase().contains("f") && move.toLowerCase().contains("f") ||
                    checkMove.toLowerCase().contains("f") && move.toLowerCase().contains("b") ||
                    checkMove.toLowerCase().contains("b") && move.toLowerCase().contains("b") ||
                    checkMove.toLowerCase().contains("b") && move.toLowerCase().contains("f"))) {
                return true;
            }
        }

        return true;
    }

    public String generateScramble() {

        StringBuilder scrambleBuild = new StringBuilder();

        int scrambleLength;
        String[] faceSet;
        String[] dirSet;

        int curMoveNum,curDirNum;
        String curMove = "";
        String curDir = "";

        boolean moveValid;

        if (puzzleType.equals("333")) {
            scrambleLength = 25;
            faceSet = faces333.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 2;
        } else if (puzzleType.equals("222")) {
            scrambleLength = 15;
            faceSet = faces222.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 1;
        } else if (puzzleType.equals("444")) {
            scrambleLength = 40;
            faceSet = faces444_555.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 4;
        } else if (puzzleType.equals("555")) {
            scrambleLength = 60;
            faceSet = faces444_555.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 4;
        } else if (puzzleType.equals("666")) {
            scrambleLength = 80;
            faceSet = faces666_777.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 6;
        } else if (puzzleType.equals("777")) {
            scrambleLength = 100;
            faceSet = faces666_777.clone();
            dirSet = dirsCubic.clone();
            turnableLayersPerAxis = 6;
        } else {
            scrambleLength = 1;
            faceSet = facesError.clone();
            dirSet = new String[]{""};
            turnableLayersPerAxis = 1;
        }

        String[] moveQueue = new String[turnableLayersPerAxis];

        for (int i = 0; i < scrambleLength; i++) {

            moveValid = false;

            while (!moveValid) {

                curMoveNum = (int) (Math.random() * faceSet.length);
                curMove = faceSet[curMoveNum];

                moveValid = validMoveCheck(curMove,moveQueue);
            }

            scrambleBuild.append(curMove);

            curDirNum = (int) (Math.random() * dirSet.length);
            curDir = dirSet[curDirNum];

            scrambleBuild.append(curDir);

            if (i < scrambleLength - 1) scrambleBuild.append(" ");

            for (int j = turnableLayersPerAxis - 2; j >= 0 ; j--) {
                moveQueue[j+1] = moveQueue[j];
            }
            moveQueue[0] = curMove;
        }

        return scrambleBuild.toString();

    }

}

