package pkgCore;

import java.util.ArrayList;
import java.util.Collections;
import pkgConstants.*;
import pkgEnum.eCardNo;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;

public class HandPoker extends Hand {

	private ArrayList<CardRankCount> CRC = null;

	public HandPoker() {
		this.setHS(new HandScorePoker());
	}

	protected ArrayList<CardRankCount> getCRC() {
		return CRC;
	}

	@Override
	public HandScore ScoreHand() {

		Collections.sort(super.getCards());
		Frequency();

		if (isRoyalFlush()) {

		} else if (isStraightFlush()) {

		} else if (isFourOfAKind()) {
			
		} else if (isFullHouse()) {
			
		} else if (isFlush()) {
			
		} else if (isStraight()) {
			
		} else if (isThreeOfAKind()) {
			
		} else if (isTwoPair()) {
			
		} else if (isPair()) {
			
		} else if (isHighCard()) {
			
		}
		return null;
	}

	public boolean isRoyalFlush() {
		boolean bIsRoyalFlush = false;
		boolean RoyalFlush = false;
		if (this.isFlush() == true) {
			for (int i = 0; i < super.getCards().size() - 1; i++) {
				if(super.getCards().get(i).geteRank().getiRankNbr() - super.getCards().get(i+1).geteRank().getiRankNbr() == 1 && super.getCards().get(i).geteRank().getiRankNbr() == (14 - i)) {
					RoyalFlush = true;
				}
				else {
					RoyalFlush = false;
					break;
				}
			}
			if (RoyalFlush == true) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.RoyalFlush);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bIsRoyalFlush = true;
			}
		}
		return bIsRoyalFlush;
	}

	public boolean isStraightFlush() {
		boolean bisStraightFlush = false;
		if (this.isFlush() == true && this.isStraight() == true) {
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.StraightFlush);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
			bisStraightFlush = true;
		}
		return bisStraightFlush;
	}
	
	public boolean isFourOfAKind() {
		boolean bisFourOfAKind = false;
		if (this.getCRC().size() == 2) {
			if (this.getCRC().get(0).getiCnt() == Constants.FOUR_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.FourOfAKind);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisFourOfAKind = true;
			}
		}
		return bisFourOfAKind;
	}


	public boolean isFullHouse() {
		boolean bisFullHouse = false;
			if (this.getCRC().size() == 2) {
				if (this.getCRC().get(0).getiCnt() == Constants.THREE_OF_A_KIND && this.getCRC().get(1).getiCnt() == Constants.TWO_OF_A_KIND) {
					HandScorePoker HSP = (HandScorePoker) this.getHS();
					HSP.seteHandStrength(eHandStrength.FullHouse);
					int iGetCard = this.getCRC().get(0).getiCardPosition();
					HSP.setHiCard(this.getCards().get(iGetCard));
					HSP.setLoCard(null);
					HSP.setKickers(FindTheKickers(this.getCRC()));
					this.setHS(HSP);
					bisFullHouse = true;
				}
		}
		return bisFullHouse;

	}

	public boolean isFlush() {
		boolean bisFlush = false;

		int iCardCnt = super.getCards().size();
		int iSuitCnt = 0;

		for (eSuit eSuit : eSuit.values()) {
			for (Card c : super.getCards()) {
				if (eSuit == c.geteSuit()) {
					iSuitCnt++;
				}
			}
			if (iSuitCnt > 0)
				break;
		}

		if (iSuitCnt == iCardCnt) {
			bisFlush = true;
			HandScorePoker HSP = (HandScorePoker) this.getHS();
			HSP.seteHandStrength(eHandStrength.Flush);
			int iGetCard = this.getCRC().get(0).getiCardPosition();
			HSP.setHiCard(this.getCards().get(iGetCard));
			HSP.setLoCard(null);
			HSP.setKickers(FindTheKickers(this.getCRC()));
			this.setHS(HSP);
		}
		else {
			bisFlush = false;
		}
		return bisFlush;
	}
	

	public boolean isStraight() {
        boolean bisStraight = false;
        boolean straight = false;
        for(int i = 0; i < super.getCards().size() - 1; i++) {
            if(super.getCards().get(i).geteRank().getiRankNbr() - super.getCards().get(i+1).geteRank().getiRankNbr() == 1) {
                straight = true;
            }
            else {
                straight = false;
                break;
            }
        }
        if(straight == true) {
            HandScorePoker HSP = (HandScorePoker) this.getHS();
            HSP.seteHandStrength(eHandStrength.Straight);
            int iGetCard = this.getCRC().get(0).getiCardPosition();
            HSP.setHiCard(this.getCards().get(iGetCard));
            HSP.setLoCard(null);
            HSP.setKickers(FindTheKickers(this.getCRC()));
            this.setHS(HSP);
            bisStraight = true;
        }
        return bisStraight;
    }

	// This is how to implement one of the 'counting' hand types. Testing to see if
	// there are three of the same rank.
	public boolean isThreeOfAKind() {
		boolean bisThreeOfAKind = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == Constants.THREE_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.ThreeOfAKind);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisThreeOfAKind = true;
			}
		}
		return bisThreeOfAKind;
	}

	public boolean isTwoPair() {
		boolean bisTwoPair = false;
		if (this.getCRC().size() == 3) {
			if (this.getCRC().get(0).getiCnt() == Constants.TWO_OF_A_KIND && this.getCRC().get(1).getiCnt() == Constants.TWO_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.TwoPair);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisTwoPair = true;
			}
		}
		return bisTwoPair;
	}

	public boolean isPair() {
		boolean bisPair = false;
		if (this.getCRC().size() == 4) {
			if (this.getCRC().get(0).getiCnt() == Constants.TWO_OF_A_KIND) {
				HandScorePoker HSP = (HandScorePoker) this.getHS();
				HSP.seteHandStrength(eHandStrength.Pair);
				int iGetCard = this.getCRC().get(0).getiCardPosition();
				HSP.setHiCard(this.getCards().get(iGetCard));
				HSP.setLoCard(null);
				HSP.setKickers(FindTheKickers(this.getCRC()));
				this.setHS(HSP);
				bisPair = true;
			}
		}
		return bisPair;
	}

	public boolean isHighCard() {
		boolean bisHighCard = false;
		HandScorePoker HSP = (HandScorePoker) this.getHS();
		HSP.seteHandStrength(eHandStrength.HighCard);
		int iGetCard = this.getCRC().get(0).getiCardPosition();
		HSP.setHiCard(this.getCards().get(iGetCard));
		HSP.setLoCard(null);
		HSP.setKickers(FindTheKickers(this.getCRC()));
		this.setHS(HSP);
		bisHighCard = true;
		return bisHighCard;
	}

	private ArrayList<Card> FindTheKickers(ArrayList<CardRankCount> CRC) {
		ArrayList<Card> kickers = new ArrayList<Card>();

		for (CardRankCount crcCheck : CRC) {
			if (crcCheck.getiCnt() == 1) {
				kickers.add(this.getCards().get(crcCheck.getiCardPosition()));
			}
		}

		return kickers;
	}

	private void Frequency() {
		CRC = new ArrayList<CardRankCount>();
		int iCnt = 0;
		int iPos = 0;
		for (eRank eRank : eRank.values()) {
			iCnt = (CountRank(eRank));
			if (iCnt > 0) {
				iPos = FindCardRank(eRank);
				CRC.add(new CardRankCount(eRank, iCnt, iPos));
			}
		}
		Collections.sort(CRC);
	}

	private int CountRank(eRank eRank) {
		int iCnt = 0;
		for (Card c : super.getCards()) {
			if (c.geteRank() == eRank) {
				iCnt++;
			}
		}
		return iCnt;
	}

	private int FindCardRank(eRank eRank) {
		int iPos = 0;

		for (iPos = 0; iPos < super.getCards().size(); iPos++) {
			if (super.getCards().get(iPos).geteRank() == eRank) {
				break;
			}
		}
		return iPos;
	}

}
