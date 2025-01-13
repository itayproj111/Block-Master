package com.example.proj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class BoardGame extends View {
    private Square[][] squares;
    private Board boardstatus;
    private final int NUM_OF_SQUARES = 8;
    private boolean firsttimeopengame = true, firsttimelost=true,firsttimepickup=true,isBlockTouched = false;
    private ArrayList<Block_Master> blockArrayList;
    private Paint p;
    private String[] blocksColors = {"Blue", "Red", "Yellow","Green","Pink"};
    private int[] blocksColor = {Color.BLUE, Color.RED, Color.YELLOW,Color.GREEN,Color.argb(255,255,182,193)};
    private Block_Master[] blockarr;
    private int currentPlacementIndex;
    private int totalblocks = 11;
    private int canvashigh, canvaswidth;
    private Context context; // game_activity
    private final int smallsize = 2;//מקטין את צורת הבלוקים פי המספר
    private int w_h;//אורך הריבוע קבוע גם ללוח וגם לצורות גם באורך וגם ברוחב הצורה
    private int startx , starty ;//שומר את המקום ההתחלתי של הבלוק והסוג שלו במספר כאשר 1 הוא בלוק שוכב 2 בלוק עומד והאלה
    private int countblockplacesd = 0;


    public BoardGame(Context context) {
        super(context);
        this.context = context;
        squares = new Square[NUM_OF_SQUARES][NUM_OF_SQUARES];
        blockarr = new Block_Master[totalblocks];
        blockArrayList = new ArrayList<>();
        p = new Paint();
        boardstatus = new Board(NUM_OF_SQUARES);

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (firsttimeopengame) {
            canvashigh = canvas.getHeight();
            canvaswidth = canvas.getWidth();
            initBorad(canvas);//יוצר במערך דו ממדי את המיקום של כל בלוק
            initBlocks(canvas);// יוצר את כל הבלוקים במערך
            Rollblocks();
            firsttimeopengame = false;
        }

        drawBoard(canvas);
        drawBlocks(canvas);
        drawlines(canvas);
    }

    private void drawBlocks(Canvas canvas) {
        for (int i = 0; i < blockArrayList.size(); i++) {
            blockArrayList.get(i).BuildBlock(canvas);
        }
    }

    private void initBlocks(Canvas canvas) {
        blockarr[0] = new Block_Master_Lying(200, 0, Color.RED, w_h / smallsize, w_h / smallsize, 2);
        blockarr[1] = new Block_Master_Stand(200, 0, Color.RED, w_h / smallsize, w_h / smallsize, 2);
        for (int i = 1; i < 4; i++) {
            blockarr[i + 1] = new Block_Master_Lying(200, 0, Color.RED, w_h / smallsize, w_h / smallsize, i + 2);
            blockarr[i + 4] = new Block_Master_Stand(200, 0, Color.RED, w_h / smallsize, w_h / smallsize, i + 2);
            blockarr[i + 7] = new Block_Master_Square(200, 0, Color.RED, w_h / smallsize, w_h / smallsize, i);
        }

    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < NUM_OF_SQUARES; i++) {
            for (int j = 0; j < NUM_OF_SQUARES; j++) {
                squares[i][j].draw(canvas);

            }

        }
    }

    private void initBorad(Canvas canvas) {
        w_h = canvas.getWidth() / NUM_OF_SQUARES;
        int x = 0;
        int y = 0;

        for (int i = 0; i < NUM_OF_SQUARES; i++) {
            for (int j = 0; j < squares.length; j++) {


                squares[i][j] = new Square(x, y, w_h, w_h);
                x = x + w_h;

            }
            x = 0;
            y = y + w_h;
        }
    }


    private void drawlines(Canvas canvas) {
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        int color = Color.BLACK;
        p = new Paint();
        p.setColor(color);
        p.setStrokeWidth(10);
        for (int i = 0; i <= NUM_OF_SQUARES; i++) {
            canvas.drawLine(x1, y1, x1, w_h * NUM_OF_SQUARES + y1, p);
            x1 = x1 + w_h;
            canvas.drawLine(x2, y2, x2 + w_h * NUM_OF_SQUARES, y2, p);
            y2 = y2 + w_h;

        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentPlacementIndex = -1;
            isBlockTouched = false;
            firsttimepickup = true;

            for (int i = 0; i < blockArrayList.size(); i++) {
                if (isBlockTouched == false) {
                    if (blockArrayList.get(i).diduserTouchMe(event.getX(), event.getY()) && !blockArrayList.get(i).isIsused()) {
                        isBlockTouched = true;
                        currentPlacementIndex = i;
                        startx = blockArrayList.get(currentPlacementIndex).getX();
                        starty = blockArrayList.get(currentPlacementIndex).getY();
                    }
                }

            }

        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (isBlockTouched) {


                blockArrayList.get(currentPlacementIndex).setX((int) event.getX());
                blockArrayList.get(currentPlacementIndex).setY((int) event.getY());

                if (firsttimepickup){
                    firsttimepickup=false;
                    blockArrayList.get(currentPlacementIndex).resetsizeblocktonormal(smallsize);
                }

            }


        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isBlockTouched){
                blockArrayList.get(currentPlacementIndex).resetsizeblocktosmall(smallsize);
                int shapekind = blockArrayList.get(currentPlacementIndex).getShapekind();
                int lengthblock = getlengthblock(blockArrayList.get(currentPlacementIndex));
                int placei = getiandj((int) event.getX(), (int) event.getY())/10;
                int placej = getiandj((int) event.getX(), (int) event.getY())%10;
                if (canitfit(placei,placej, shapekind, lengthblock)){
                    int colorblock = blockArrayList.get(currentPlacementIndex).getColor();
                    blockArrayList.remove(blockArrayList.get(currentPlacementIndex));
                    UpdateBoardStatus(placei,placej,shapekind, lengthblock, colorblock);
                    countblockplacesd++;
                    UpdateBoardGame();
                    if (countblockplacesd!=3){
                        whatleftcanfit();
                    }
                }
                else {
                    blockArrayList.get(currentPlacementIndex).setX(startx);
                    blockArrayList.get(currentPlacementIndex).setY(starty);

                }

            }

            startx = 0;
            starty =0;
            if(countblockplacesd==3){
                Rollblocks();
                countblockplacesd= 0;
            }
            //מאפס את הערכים

        }
        invalidate();
        return true;
    }

    private void UpdateBoardGame() {
        ArrayList<Integer> line = new ArrayList<>();
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < NUM_OF_SQUARES; i++) {
            if (boardstatus.islinefull(i)){
                line.add(i);
            }
            if (boardstatus.isrowfull(i)){
                row.add(i);
            }
        }
        ((Game_Activity)context).updatepoints(line.size(),row.size());
        for (int i = 0; i < line.size(); i++) {
            boardstatus.cleanline(line.get(i));
            cleanline(line.get(i));
        }
        for (int i = 0; i < row.size(); i++) {
            boardstatus.cleanrow(row.get(i));
            cleanrow(row.get(i));
        }

    }

    private void cleanrow(int i) {
        for (int j = 0; j < NUM_OF_SQUARES; j++) {
            int x = squares[i][j].getX();
            int y = squares[i][j].getY();
            for (int k = 0; k <blockArrayList.size() ; k++) {
                if (blockArrayList.get(k).getX()==x&& blockArrayList.get(k).getY()==y){
                    blockArrayList.remove(blockArrayList.get(k));
                }
            }
        }
    }

    private void cleanline(int i) {
        for (int j = 0; j < NUM_OF_SQUARES; j++) {
            int x = squares[j][i].getX();
            int y = squares[j][i].getY();
            for (int k = 0; k <blockArrayList.size() ; k++) {
                if (blockArrayList.get(k).getX()==x&& blockArrayList.get(k).getY()==y){
                    blockArrayList.remove(blockArrayList.get(k));
                }
            }
        }
    }


    private void createDialog() {
        if(firsttimelost == true){
            Dialog_SecondChanse customDialog = new Dialog_SecondChanse(context);
            customDialog.show();
        }
        else {
            Dialog_GameOver customDialog = new Dialog_GameOver(context);
            customDialog.show();
        }

    }

    public void Rollblocks() {
        Random rnd = new Random();
        boolean flag = true;
        int randomblock1 = rnd.nextInt(totalblocks);
        while (flag){
            randomblock1 = rnd.nextInt(totalblocks);
            int shapekind = blockarr[randomblock1].getShapekind();
            int lengthblock = getlengthblock(blockarr[randomblock1]);
            for (int i = 0; i <NUM_OF_SQUARES ; i++) {
                for (int j = 0; j <NUM_OF_SQUARES ; j++) {
                    if (canitfit(i,j,shapekind,lengthblock))
                        flag = false;
                }
            }
        }

        flag= true;
        while (flag) {
            int numcolorblock1 = rnd.nextInt(blocksColors.length);
            int numcolorblock2 = rnd.nextInt(blocksColors.length);
            int numcolorblock3 = rnd.nextInt(blocksColors.length);
            int randomblock2 = rnd.nextInt(totalblocks);
            int randomblock3 = rnd.nextInt(totalblocks);
            if (randomblock1 != randomblock2 && randomblock1 != randomblock3 && randomblock2 != randomblock3 && !blocksColors[numcolorblock1].equals(((Game_Activity) context).getBackgroundColor()) && !blocksColors[numcolorblock2].equals(((Game_Activity) context).getBackgroundColor()) && !blocksColors[numcolorblock3].equals(((Game_Activity) context).getBackgroundColor())) {
                blockarr[randomblock1].setX(canvaswidth / 18);
                blockarr[randomblock1].setY(canvashigh - canvashigh / 5);
                blockarr[randomblock1].setColor(blocksColor[numcolorblock1]);
                blockArrayList.add(blockarr[randomblock1]);
                blockarr[randomblock2].setX(canvaswidth / 3 + canvaswidth / 18);
                blockarr[randomblock2].setY(canvashigh - canvashigh / 5);
                blockarr[randomblock2].setColor(blocksColor[numcolorblock2]);
                blockArrayList.add(blockarr[randomblock2]);
                blockarr[randomblock3].setX(canvaswidth / 3 * 2 + canvaswidth / 18);
                blockarr[randomblock3].setY(canvashigh - canvashigh / 5);
                blockarr[randomblock3].setColor(blocksColor[numcolorblock3]);
                blockArrayList.add(blockarr[randomblock3]);
                flag = false;
            }
        }
    }

    private int getlengthblock(Block_Master randomblock) {

        if (randomblock instanceof Block_Master_Lying) {
            return ((Block_Master_Lying) randomblock).getLyingnumber();
        }
        if (randomblock instanceof Block_Master_Stand) {
            return ((Block_Master_Stand) randomblock).getStandingnumber();
        }
        if (randomblock instanceof Block_Master_Square) {
            return ((Block_Master_Square) randomblock).getSquaresize();
        }
         return -1;
    }

    public void whatleftcanfit(){
        boolean flag = false;
        for (int i = 0; i < blockArrayList.size(); i++) {
            if (blockArrayList.get(i).getY()==(canvashigh - canvashigh / 5)&&(blockArrayList.get(i).getX()==(canvaswidth / 18)||blockArrayList.get(i).getX()==(canvaswidth / 3 + canvaswidth / 18)||blockArrayList.get(i).getX()==(canvaswidth / 3 * 2 + canvaswidth / 18))){
                int kind = blockArrayList.get(i).shapekind;
                int lengthblock = getlengthblock(blockArrayList.get(i));
                for (int k = 0; k <NUM_OF_SQUARES ; k++) {
                    for (int j = 0; j <NUM_OF_SQUARES ; j++) {
                        if (canitfit(k,j,kind,lengthblock))
                            flag = true;
                    }
                }
            }
        }
        if(!flag)
            createDialog();

    }
    public boolean canitfit(int placei , int placej,int kind, int lenth){
        if (placei != -1) {
            for (int i = 0; i < lenth; i++) {
                if (kind == 1) {
                    if (placej + lenth - 1 >= NUM_OF_SQUARES) {
                        return false;
                    }
                    if (!boardstatus.checkisEmpty(placei, placej + i)) {
                        return false;
                    }
                }
                if (kind == 2) {
                    if (placei + lenth - 1 >= NUM_OF_SQUARES) {
                        return false;
                    }
                    if (!boardstatus.checkisEmpty(placei + i, placej)) {
                        return false;
                    }

                }
                if (kind == 3) {
                    if (placei + lenth - 1 >= NUM_OF_SQUARES || placej + lenth - 1 >= NUM_OF_SQUARES) {
                        return false;
                    }
                    if (lenth > 2) {
                        if (!boardstatus.checkisEmpty(placei + 1, placej + 1))//בודק את אמצע הריבוע
                            return false;
                    }
                    if (!(boardstatus.checkisEmpty(placei, placej + i) && boardstatus.checkisEmpty(placei + i, placej) && boardstatus.checkisEmpty(placei + lenth - 1, placej + i) && boardstatus.checkisEmpty(placei + i, placej + lenth - 1))) {
                        return false;
                    }
                }
            }
        }
        else {
            return false;
        }
        return true;
    }

    public int getiandj(int x, int y) {
        int placei = -1, placej = -1;
        boolean flag = true;// אם מוקם במקום תקין
        for (int i = 0; i < NUM_OF_SQUARES; i++) {
            for (int j = 0; j < NUM_OF_SQUARES; j++) {
                if (squares[i][j].diduserTouchMe(x, y)) {
                    placei = i;
                    placej = j;
                }
            }
        }
       return placei*10+placej;
    }
    private void UpdateBoardStatus(int placei, int placej, int kind, int length, int colorblock) {



            for (int i = 0; i < length; i++) {
                if (kind == 1) {
                    boardstatus.changeblockstatus(placei, placej + i, 1);
                    blockArrayList.add(new Block_Master_Square(squares[placei][placej + i].getX(), squares[placei][placej + i].getY(), colorblock, w_h, w_h, 1));
                    blockArrayList.get(blockArrayList.size() - 1).setIsused(true);
                    ((Game_Activity)context).addonepoint();
                }
                if (kind == 2) {
                    boardstatus.changeblockstatus(placei + i, placej, 1);
                    blockArrayList.add(new Block_Master_Square(squares[placei + i][placej].getX(), squares[placei + i][placej].getY(), colorblock, w_h, w_h, 1));
                    blockArrayList.get(blockArrayList.size() - 1).setIsused(true);
                    ((Game_Activity)context).addonepoint();
                }
                if (kind == 3) {
                    for (int j = 0; j < length; j++) {
                        boardstatus.changeblockstatus(placei+i,placej+j,1);
                        blockArrayList.add(new Block_Master_Square(squares[placei + i][placej+j].getX(), squares[placei + i][placej].getY(), colorblock, w_h, w_h, 1));
                        blockArrayList.get(blockArrayList.size() - 1).setIsused(true);
                        ((Game_Activity)context).addonepoint();
                    }

                }
            }



    }

    public void reset() {
        firsttimeopengame = true;
        invalidate();
    }
}

