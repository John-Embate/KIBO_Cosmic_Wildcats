package jp.jaxa.iss.kibo.rpc.cosmic_wildcats;


import gov.nasa.arc.astrobee.Result;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;

import org.opencv.core.Mat;

import jp.jaxa.iss.kibo.rpc.cosmic_wildcats.OpenCVHelper;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee.
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){

        Result result;
        final int LOOP_MAX = 3;
        int  loopCounter = 0;

        // The mission starts.
        api.startMission();

        // Move to a point.
        Point point1 = new Point(10.75d, -9.92d, 5.3d);
        Quaternion quaternion1 = new Quaternion(0f, 0f, -0.707f, 0.707f);
        result = api.moveTo(point1, quaternion1, true);

        //For tolerance violations and collision detection
        while(!result.hasSucceeded() && loopCounter < LOOP_MAX){
            //retry
            result = api.moveTo(point1, quaternion1, true);
            ++loopCounter;
        }

        loopCounter = 0; //start to zero for loop counter
        // Get a camera image.
        Mat image = api.getMatNavCam();

        String objectName = model.identifyObject(image);
        int objectCount = model.countObject(image);

        api.setAreaInfo(1, objectName, objectCount);
        api.saveMatImage(image, "prelim_image.png");

        /* *********************************************************************** */
        /* Write your code to recognize type and number of items in the each area! */
        /* *********************************************************************** */

        // When you recognize items, let’s set the type and number.


        /* **************************************************** */
        /* Let's move to the each area and recognize the items. */
        /* **************************************************** */

        // When you move to the front of the astronaut, report the rounding completion.
        api.reportRoundingCompletion();

        /* ********************************************************** */
        /* Write your code to recognize which item the astronaut has. */
        /* ********************************************************** */

        // Let's notify the astronaut when you recognize it.
        api.notifyRecognitionItem();

        /* ******************************************************************************************************* */
        /* Write your code to move Astrobee to the location of the target item (what the astronaut is looking for) */
        /* ******************************************************************************************************* */

        // Take a snapshot of the target item.
        api.takeTargetItemSnapshot();
    }

    @Override
    protected void runPlan2(){
       // write your plan 2 here.
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here.
    }

    // You can add your method.
    private String yourMethod(){
        return "your method";
    }
}

