package jp.jaxa.iss.kibo.rpc.cosmic_wildcats;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class OpenCVHelper {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    private CascadeClassifier classifier;

    public OpenCVHelper(String xmlFile) {
        classifier = new CascadeClassifier(xmlFile);
    }

    public String identify(Mat image) {
        MatOfRect objectDetections = new MatOfRect();
        classifier.detectMultiScale(image, objectDetections);
        // Assuming first object's name as an example
        return "IdentifiedObject"; // Modify based on actual identification logic
    }

    public int countObjects(Mat image) {
        MatOfRect objectDetections = new MatOfRect();
        classifier.detectMultiScale(image, objectDetections);
        return objectDetections.toArray().length;
    }
}
