package com.example.gryzhuk.perpetualmotion.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.gryzhuk.perpetualmotion.R;
import com.example.perpetual_motion.pm_game.Card;

public class CardPilesAdapter extends RecyclerView.Adapter<CardPilesAdapter.ViewHolder> {

    //primary data source
    private final Card[] mPile_TOPS;
    //which cards are (un)checked
    private final boolean[] mCHECKED_PILES;
    //how many cards are in each pile (cards in stack)
    private final int[] mNUMBER_OF_CARDS_IN_PILE;
    //string from XML main activity into constructor
    private final String mMSG_CARDS_IN_STACK;


    public CardPilesAdapter(Context context, int msgCardsInStack) {
        final int NUMBER_OF_PILES=4;
        mPile_TOPS = new Card[NUMBER_OF_PILES];
        mCHECKED_PILES = new boolean[NUMBER_OF_PILES];
        mNUMBER_OF_CARDS_IN_PILE = new int[NUMBER_OF_PILES];

        mMSG_CARDS_IN_STACK = context.getString(msgCardsInStack);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutVIew = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.rv_item_pile_top_card, viewGroup, false);
        return new ViewHolder(itemLayoutVIew);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        updateOuterCard(viewHolder, position);
        updateInnerCard(viewHolder, position);
    }

    private void updateOuterCard(ViewHolder viewHolder, int position) {
        viewHolder.tv_pile_card_cards_in_stack.setText(mMSG_CARDS_IN_STACK.concat(Integer.toString(mNUMBER_OF_CARDS_IN_PILE[position])));
    }
    private void updateInnerCard(ViewHolder viewHolder, int position) {
        if (mPile_TOPS[position]!=null){
            populateAndShowInnerCard (viewHolder, position);
        } else clearAndHideInnerCard(viewHolder);
    }

    private void populateAndShowInnerCard(ViewHolder viewHolder, int position) {
        Card currentCard = mPile_TOPS[position];
        int currentColor = currentCard.getSuit().getColor();

        viewHolder.tv_pile_card_rank_top.setTextColor(currentColor);
        viewHolder.tv_pile_card_rank_top.setText(currentCard.getRank().getValue());

        viewHolder.tv_pile_card_suit_center.setTextColor(currentColor);
        viewHolder.tv_pile_card_suit_center.setText(currentCard.getSuit().getCharacter());

        viewHolder.cv_pile_inner_card.setVisibility(View.VISIBLE);
    }

    private void clearAndHideInnerCard(ViewHolder viewHolder) {
        viewHolder.tv_pile_card_rank_top.setText("");
        viewHolder.tv_pile_card_suit_center.setText("");
        viewHolder.tv_pile_card_name_bottom.setText("");
        viewHolder.cb_pile_card_checkbox.setChecked(false);

        viewHolder.cv_pile_inner_card.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //java mirror of rv_item....xml
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView cv_pile_inner_card;
        public final CheckBox cb_pile_card_checkbox;
        public final TextView tv_pile_card_rank_top, tv_pile_card_name_bottom,
                    tv_pile_card_suit_center, tv_pile_card_cards_in_stack;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_pile_inner_card = itemView.findViewById(R.id.pile_card_inner_card);
            cb_pile_card_checkbox = itemView.findViewById(R.id.pile_card_checkbox);
            tv_pile_card_rank_top = itemView.findViewById(R.id.pile_card_rank_top);
            tv_pile_card_name_bottom = itemView.findViewById(R.id.pile_card_name_bottom);
            tv_pile_card_suit_center = itemView.findViewById(R.id.pile_card_suit_center);
            tv_pile_card_cards_in_stack = itemView.findViewById(R.id.pile_card_in_stack);

        }
    }
}
