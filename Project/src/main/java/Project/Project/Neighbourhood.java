package Project.Project;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Neighbourhood {
	public static void main(String[] args) {
	        //user id for which we need recommendations
	        long userid = 574434;
	        //number of recommendations needed
	        int recommendationsno = 5;
	        try {
	        	//creation of a data model from the input file
	            FileDataModel fileDataModel = new FileDataModel(new File("C:\\Users\\rametta\\Downloads\\dataInFormat.txt"));
	            //defining a notion of similarity between two users
	            UserSimilarity userRatingSimilarity = new PearsonCorrelationSimilarity(fileDataModel);
	            //finding 100 nearest neighbors
	            UserNeighborhood userCalcNeighborhood = new NearestNUserNeighborhood(50, userRatingSimilarity, fileDataModel);
	            //creating a recommender from the given data model and user similarity
	            Recommender recommender = new GenericUserBasedRecommender(fileDataModel, userCalcNeighborhood, userRatingSimilarity);
	            //generating recommendations from the recommender
	            List<RecommendedItem> finalUserRecommendationsMade = recommender.recommend(userid, recommendationsno);
	            //iterating through the recommendations
	            for (RecommendedItem toUserRecommendedItem : finalUserRecommendationsMade) {
	                System.out.println(toUserRecommendedItem.getItemID());
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (TasteException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
}
