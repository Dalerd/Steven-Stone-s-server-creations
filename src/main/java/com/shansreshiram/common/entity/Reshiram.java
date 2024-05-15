package com.shansreshiram.common.entity;

import com.shansreshiram.common.entity.core.ai.goal.FlyGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Reshiram extends PathAwareEntity implements IAnimatable, IAnimationTickable {
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private boolean isAnimating = false;
    public Reshiram(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isAnimating) {
            event.getController()
                    .setAnimation(new AnimationBuilder().addAnimation("IDLE", ILoopType.EDefaultLoopTypes.LOOP)
                            .addAnimation("WALK", ILoopType.EDefaultLoopTypes.LOOP)
                            .addAnimation("IDLEFLY", ILoopType.EDefaultLoopTypes.LOOP)
                            .addAnimation("FLY", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController<Reshiram> controller = new AnimationController<>(this, "controller", 0,
                this::predicate);

    }


    @Override
    public AnimationFactory getFactory() {
        return null;
    }
    @Override
    protected void intiGoals{
        this.goalSelector.add(1, new LookAroundGoal(this));
        this.goalSelector.add(2, new FlyGoal(this));
        this.goalSelector.add(3, FireballEntity(this));
    }

    @Override
    public int tickTimer() {
        return 0;
    }
}
