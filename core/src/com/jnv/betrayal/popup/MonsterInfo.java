package com.jnv.betrayal.popup;


import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jnv.betrayal.gameobjects.Monster;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.resources.FontManager;

public class MonsterInfo extends Popup{

    private Image monster_image;
    private Image background;
    private Label name_label,attack_label,defense_label,health_label;
    private String name;
    private int attack,defense,health,skill1, skill2,skill3;
    private int current_monster_health, monster_image_height, monster_image_width;

    public MonsterInfo(Betrayal game, Monster monster, int curHealth ) {
        super(game);
        monster_image = new Image(monster.getMonsterTexture());
        monster_image_height = monster.getHeight();
        monster_image_width = monster.getWidth();
        current_monster_health = curHealth;
        name = monster.getNickname();
        attack = monster.getAttack();
        defense = monster.getDefense();
        health = monster.getHealth();
        skill1 = monster.getSkill1();
        skill2 = monster.getSkill2();
        skill3 = monster.getSkill3();
        loadButtons();
    }

    private void loadButtons() {
        loadBackground();
        loadTitle();
        loadMonsterImage();
        loadMonsterStats();
    }

    private void loadBackground() {
        background = new Image(res.getTexture("confirmation-background"));
        background.layout();
        background.setBounds(100, 200, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 400);
        popup.addActor(background);
    }

    private void loadTitle() {
        name_label = new Label(name, FontManager.getFont(40));
        name_label.setX((Betrayal.WIDTH + name_label.getWidth()) / 2);
        name_label.setY(Betrayal.HEIGHT - 200 - 50);
        popup.addActor( name_label);
    }

    private void loadMonsterImage(){
        monster_image.layout();
        monster_image.setBounds((Betrayal.WIDTH + monster_image_width) / 2, Betrayal.HEIGHT - 60 - monster_image_height,
                monster_image_width, monster_image_height);
        popup.addActor(monster_image);

    }

    private void loadMonsterStats() {
        attack_label = new Label("Attack: "+ attack, FontManager.getFont(25));
        attack_label.setX(110);
        attack_label.setY(Betrayal.HEIGHT-270-monster_image_height);
        popup.addActor(attack_label);

        defense_label = new Label("Defense: "+ defense, FontManager.getFont(25));
        defense_label.setX(110);
        defense_label.setY(Betrayal.HEIGHT-305-monster_image_height);
        popup.addActor(defense_label);

        health_label = new Label("Health: "+current_monster_health+" / "+health, FontManager.getFont(25));
        health_label.setX(110);
        health_label.setY(Betrayal.HEIGHT-330-monster_image_height);
        popup.addActor( health_label);
    }

    public void doSomething() {
    }
}
