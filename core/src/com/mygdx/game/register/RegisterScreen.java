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
import dao.UserDao;
import model.Global;
import model.User;

import java.sql.SQLException;

public class RegisterScreen extends CommonScreen {

    Stage stage;
    Sprite bg;
    TextField textFieldAccount, textFieldPassword;
    Button enterGame;


    public RegisterScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
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
        game.getBatch().begin();
        bg.draw(game.getBatch());
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
            return;
        }

        if (user.tryLogin(account, password)) {
            Global.currentUser = user;
        }
    }
}
