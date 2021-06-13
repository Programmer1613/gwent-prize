package com.mygdx.game.register;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.common.CommonScreen;
import com.mygdx.game.dao.UserDao;
import com.mygdx.game.main_menu.Cover;
import com.mygdx.game.model.Global;
import com.mygdx.game.model.User;

import java.sql.SQLException;

public class RegisterScreen extends CommonScreen {

    Stage stage;
    Sprite bg;
    TextField textFieldAccount, textFieldPassword;
    Button enterGame;
    BitmapFont front;
    String currentHint = "Please Input account & Password";


    public RegisterScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        front = new BitmapFont();
        front.setColor(1, 0, 0, 1);
        front.getData().setScale(1.0f);

        bg = new Sprite(new Texture("texture/register_bg"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(
                new BitmapFont(),
                new Color(1, 1, 1, 1),
                null, null,
                new TextureRegionDrawable(new Texture("texture/register_input"))
        );
        textFieldAccount = new TextField("账号", style);
        textFieldAccount.setSize(250, 50);
        textFieldAccount.setPosition(100, 300);
        textFieldAccount.setMaxLength(20);
        textFieldPassword = new TextField("密码", style);
        textFieldPassword.setSize(250, 50);
        textFieldPassword.setPosition(100, 200);
        textFieldPassword.setMaxLength(20);
        textFieldPassword.setPasswordMode(true);
        textFieldPassword.setPasswordCharacter('*');

        enterGame = new Button(new TextureRegionDrawable(new Texture("texture/enter_game")));
        enterGame.setPosition(100, 150);
        enterGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                processRegister();
            }
        });

        stage.addActor(textFieldAccount);
        stage.addActor(textFieldPassword);
        stage.addActor(enterGame);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        bg.draw(batch);
        front.draw(batch, currentHint, 100, 150);
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    protected void processRegister() {
        String account, password;
        account = textFieldAccount.getText();
        password = textFieldPassword.getText();
        User user;
        try {
            UserDao.get().createIfNotExists(new User(account, password));
            user = UserDao.get().queryForId(account);
        } catch (SQLException e) {
            Gdx.app.log("sql", e.toString());
            return;
        }

        if (user.tryLogin(account, password)) {
            Global.currentUser = user;
            currentHint = "login success";
        } else if (user.getIsActive()) {
            currentHint = "password wrong";
        } else {
            currentHint = "wait to active account";
        }
    }
}
