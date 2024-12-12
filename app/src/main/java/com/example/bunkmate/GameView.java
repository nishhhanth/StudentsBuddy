package com.example.bunkmate;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    private int screenWidth, screenHeight;
    private Paint paint;
    private Rect paddle;
    private float paddleSpeed = 10;
    private float paddleX;
    private float paddleY;
    private float ballX, ballY;
    private float ballSpeedX = 5, ballSpeedY = 5;
    private int ballRadius = 30;
    private boolean isGameOver = false;

    private int score = 0; // Variable to keep track of the score
    private int highestScore = 0; // Variable to store the highest score
    private static final float MAX_BALL_SPEED = 20.0f; // Maximum ball speed
    private SharedPreferences sharedPreferences;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paddle = new Rect(0, 0, 200, 30); // Initialize paddle size
        paddleX = 100;
        paddleY = 1000;

        // Initialize SharedPreferences to store the highest score
        sharedPreferences = context.getSharedPreferences("GamePrefs", Context.MODE_PRIVATE);
        highestScore = sharedPreferences.getInt("highestScore", 0); // Retrieve the highest score from preferences
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        resetGame(); // Reset game when size changes (this also initializes positions)
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isGameOver) {
            paint.setTextSize(100);
            paint.setColor(Color.RED);
            canvas.drawText("Game Over", screenWidth / 4, screenHeight / 2, paint);
            paint.setTextSize(50);
            canvas.drawText("Tap to Restart", screenWidth / 3, screenHeight / 2 + 100, paint);
            return;
        }

        // Draw the ball
        paint.setColor(Color.RED);
        canvas.drawCircle(ballX, ballY, ballRadius, paint);

        // Draw the paddle
        paint.setColor(Color.GREEN);
        canvas.drawRect(paddleX, paddleY, paddleX + paddle.width(), paddleY + paddle.height(), paint);

        // Draw the score
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 50f, 50f, paint);

        // Draw the highest score
        canvas.drawText("Highest Score: " + highestScore, screenWidth - 350f, 50f, paint);

        // Update ball position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Check for collision with walls
        if (ballX < ballRadius || ballX > screenWidth - ballRadius) {
            ballSpeedX = -ballSpeedX;
        }
        if (ballY < ballRadius) {
            ballSpeedY = -ballSpeedY;
        }

        // Check if the ball hits the paddle
        if (ballY + ballRadius >= paddleY && ballX >= paddleX && ballX <= paddleX + paddle.width()) {
            ballSpeedY = -ballSpeedY;

            // Increase ball speed after hitting the paddle
            increaseBallSpeed();

            // Increment the score
            score++;
        }

        // Check if the ball falls below the paddle (game over)
        if (ballY > screenHeight) {
            // Update highest score if current score is higher
            if (score > highestScore) {
                highestScore = score;
                // Save the highest score in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("highestScore", highestScore);
                editor.apply();
            }
            isGameOver = true;
        }

        // Invalidate the view to call onDraw() again (game loop)
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isGameOver) {
            // If the game is over and user taps, restart the game
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                resetGame();
                invalidate(); // Refresh the view after reset
            }
        } else {
            // Handle paddle movement during the game
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                paddleX = event.getX() - paddle.width() / 2;
            }
        }
        return true;
    }

    // Method to reset game state
    private void resetGame() {
        score = 0;
        ballX = screenWidth / 2;
        ballY = screenHeight / 2;
        ballSpeedX = 5;
        ballSpeedY = 5;
        paddleX = screenWidth / 2 - paddle.width() / 2;
        paddleY = screenHeight - 150;
        isGameOver = false;
    }

    // Method to increase ball speed
    private void increaseBallSpeed() {
        // Increase speed for both X and Y directions
        ballSpeedX *= 2; // Increase by 10%
        ballSpeedY *= 2; // Increase by 10%

        // Clamp the speed to a maximum value
        if (ballSpeedX > MAX_BALL_SPEED) {
            ballSpeedX = MAX_BALL_SPEED;
        }
        if (ballSpeedY > MAX_BALL_SPEED) {
            ballSpeedY = MAX_BALL_SPEED;
        }
    }
}
