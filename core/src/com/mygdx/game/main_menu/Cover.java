package com.mygdx.game.main_menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.dao.ActivityDao;
import com.mygdx.game.model.Activity;

import java.sql.SQLException;

public class Cover extends Button {

    public static final float maxWidth = 400;
    public static final float maxHeight = 240;

    private final String gameId;
    private int activityStatus;

    public Cover(String gameId) {
        super(new TextureRegionDrawable(new Texture("texture/cover/" + gameId)));
        this.gameId = gameId;
        initActivity();
        resetCover();
    }

    private void initActivity() {
        try {
            Activity activity = ActivityDao.get().queryForId(gameId);
            if (activity == null) {
                activityStatus = Activity.Status.waiting;
            } else {
                activityStatus = activity.getStatus();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void resetCover() {
        float width = this.getWidth(), height = this.getHeight();
        float a = Math.max(width / maxWidth, height / maxHeight);
        this.setSize(width / a, height / a);
    }
}
