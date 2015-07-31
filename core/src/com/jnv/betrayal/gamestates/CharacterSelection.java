package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.utilities.CharacterInfo;

/**
 * Sets up the character creation screen with scene2d
 * @author Vincent Wang
 */
public class CharacterSelection extends GameState {

    private Label.LabelStyle labelStyle;

    private Image field_framePreview, field_headPreview, field_armorPreview, field_weaponPreview, field_shieldPreview;
    private Label field_usernameLabel;
    private SelectionField gender, hairStyle, hairColor, skinTone;
    private CharacterInfo characterInfo;

    private TextureRegion image_leftArrow, image_rightArrow;

    private enum Trait {
        GENDER,
        HAIR_STYLE,
        HAIR_COLOR,
        SKIN_TONE
    }

    public CharacterSelection(GameStateManager gsm) {
        super(gsm);
        Gdx.app.log("log", "CharacterSelection constructor");

        image_leftArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));
        image_leftArrow.flip(true, false);
        image_rightArrow = new TextureRegion(Betrayal.res.getTexture("arrow"));

        characterInfo = new CharacterInfo();

        loadFont();
        loadActors();
    }

    public void update(float dt) {
        stage.act(dt);
    }
    public void handleInput() {

    }
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0); // Set background to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
    public void dispose() {

    }

    // Helpers
    private void loadFont() {
        labelStyle = Betrayal.getHurtmoldFontLabelStyle();
    }
    private void loadActors() {
        loadUsernameField();
        loadArmorPreview();
        loadImagePreview();

        gender = new SelectionField("Gender", Trait.GENDER);
        hairStyle = new SelectionField("Hair Style", gender.getActorReference(), Trait.HAIR_STYLE);
        hairColor = new SelectionField("Hair Color", hairStyle.getActorReference(), Trait.HAIR_COLOR);
        //skinTone = new SelectionField("Skin Tone", hairColor.getActorReference(), Trait.SKIN_TONE);

        gender.addToStage();
        hairStyle.addToStage();
        hairColor.addToStage();
        //skinTone.addToStage();
    }
    private void loadUsernameField() {
        // Username "Name:" text
        field_usernameLabel = new Label("Name: ", labelStyle);
        field_usernameLabel.setHeight(80);
        field_usernameLabel.setX(10);
        field_usernameLabel.setY(Betrayal.HEIGHT - field_usernameLabel.getHeight() * 2);
        stage.addActor(field_usernameLabel);

        // Username input text field
        TextField.TextFieldStyle tfs = new TextField.TextFieldStyle();
        tfs.font = labelStyle.font;
        tfs.messageFont = tfs.font;
        tfs.fontColor = Color.LIGHT_GRAY;
        tfs.messageFontColor = Color.GRAY;
        TextField field_usernameEnter = new TextField("", tfs);
        field_usernameEnter.setMessageText("Enter name here");
        field_usernameEnter.setBounds(10 + field_usernameLabel.getWidth() + 10, field_usernameLabel.getY(),
                Betrayal.WIDTH - 20 - field_usernameLabel.getWidth(), field_usernameLabel.getHeight());
        field_usernameEnter.setMaxLength(16);
        stage.addActor(field_usernameEnter);

        // Removes keyboard focus if tap isn't on a TextField
        stage.getRoot().addCaptureListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!(event.getTarget() instanceof TextField)) {
                    stage.setKeyboardFocus(null);
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                return false;
            }
        });

    }
    private void loadImagePreview() {
        // Create image preview box
        loadPreviewFrame();
        loadHeadPreview();

    }
    private void loadPreviewFrame() {
        field_framePreview = new Image(characterInfo.getHeadPreview());
        field_framePreview.setWidth(384);
        field_framePreview.setHeight(576);
        field_framePreview.setX(10);
        field_framePreview.setY(Betrayal.HEIGHT - 30 - field_usernameLabel.getHeight() * 3 -
                field_framePreview.getHeight());
        stage.addActor(field_framePreview);
    }
    private void loadHeadPreview() {
        field_headPreview = new Image(characterInfo.getHeadPreview());
        field_headPreview.setWidth(384);
        field_headPreview.setHeight(576);
        field_headPreview.setX(10);
        field_headPreview.setY(Betrayal.HEIGHT - 30 - field_usernameLabel.getHeight() * 3 -
                field_headPreview.getHeight());
        stage.addActor(field_headPreview);
    }
    private void loadArmorPreview() {
        field_armorPreview = new Image(characterInfo.getArmorPreview());
        field_armorPreview.setWidth(384);
        field_armorPreview.setHeight(576);
        field_armorPreview.setX(10);
        field_armorPreview.setY(Betrayal.HEIGHT - 30 - field_usernameLabel.getHeight() * 3 -
                field_armorPreview.getHeight());
        stage.addActor(field_armorPreview);
    }

    // Classes
    private class SelectionField {

        private Trait trait;

        private Label field_selection_label, field_selection_serialNumber;
        private Image field_selection_leftArrow, field_selection_rightArrow;

        /** Creates the first selection field and serves as a position reference for future
         * selection fields
         * @param label selection field name
         * @param trait specifies character trait to be edited */
        public SelectionField(String label, Trait trait) {
            this.trait = trait;

            // Create label
            field_selection_label = new Label(label, labelStyle);
            field_selection_label.setX(field_framePreview.getX()
                    + field_framePreview.getWidth() + 20);
            field_selection_label.setY(field_framePreview.getY()
                    + field_framePreview.getHeight() - field_selection_label.getHeight());
            field_selection_label.setWidth(Betrayal.WIDTH - field_selection_label.getX() - 20);
            field_selection_label.setAlignment(Align.center);

            // Create left arrow
            field_selection_leftArrow = new Image(image_leftArrow);
            field_selection_leftArrow.setBounds(field_selection_label.getX(),
                    field_selection_label.getY() - 10 - field_selection_label.getHeight(),
                    (Betrayal.WIDTH - 160 - field_framePreview.getWidth()
                            - field_framePreview.getX()) / 2, field_selection_label.getHeight());

            // Create serial number
            field_selection_serialNumber = new Label(characterInfo.getGender(), labelStyle);
            field_selection_serialNumber.setX(field_selection_leftArrow.getX()
                    + field_selection_leftArrow.getWidth() + 30);
            field_selection_serialNumber.setY(field_selection_leftArrow.getY());
            field_selection_serialNumber.setWidth(60);
            field_selection_serialNumber.setAlignment(Align.center);

            // Create right arrow
            field_selection_rightArrow = new Image(image_rightArrow);
            field_selection_rightArrow.setBounds(field_selection_serialNumber.getX()
                            + field_selection_serialNumber.getWidth() + 30,
                    field_selection_serialNumber.getY(), field_selection_leftArrow.getWidth(),
                    field_selection_leftArrow.getHeight());
        }

        /** Creates selection field based off the position of the last selection field created
         *
         * @param label name of character trait to be edited
         * @param positionReference left arrow of the previous selection field
         * @param trait specifies character trait to be edited */
        public SelectionField(String label, Actor positionReference, Trait trait) {
            this.trait = trait;

            // Create label
            field_selection_label = new Label(label, labelStyle);
            field_selection_label.setX(field_framePreview.getX()
                    + field_framePreview.getWidth() + 20);
            field_selection_label.setY(positionReference.getY() -
                    positionReference.getHeight() - 30);
            field_selection_label.setWidth(Betrayal.WIDTH - positionReference.getX() - 20);
            field_selection_label.setAlignment(Align.center);

            // Create left arrow
            field_selection_leftArrow = new Image(image_leftArrow);
            field_selection_leftArrow.setWidth((Betrayal.WIDTH - 160
                    - field_framePreview.getWidth() -
                    field_framePreview.getX()) / 2);
            field_selection_leftArrow.setHeight(field_selection_label.getHeight());
            field_selection_leftArrow.setX(field_selection_label.getX());
            field_selection_leftArrow.setY(field_selection_label.getY() - 10
                    - field_selection_leftArrow.getHeight());

            // Create serial number
            field_selection_serialNumber = new Label("1", labelStyle);
            field_selection_serialNumber.setBounds(field_selection_leftArrow.getX() +
                            field_selection_leftArrow.getWidth() + 30, field_selection_leftArrow.getY(), 60,
                    field_selection_leftArrow.getHeight());
            field_selection_serialNumber.setAlignment(Align.center);

            // Create right arrow
            field_selection_rightArrow = new Image(image_rightArrow);
            field_selection_rightArrow.setBounds(field_selection_serialNumber.getX() +
                            field_selection_serialNumber.getWidth() + 30,
                    field_selection_serialNumber.getY(),
                    field_selection_leftArrow.getWidth(), field_selection_leftArrow.getHeight());
        }

        // Helpers
        public void addToStage() {
            stage.addActor(field_selection_label);
            stage.addActor(field_selection_leftArrow);
            stage.addActor(field_selection_serialNumber);
            stage.addActor(field_selection_rightArrow);
        }

        // Getters
        public Actor getActorReference() { return field_selection_leftArrow; }
        public Trait getTrait() { return trait; }
    }
}
