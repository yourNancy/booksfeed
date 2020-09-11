package com.example.booksfeed.utilities;

import android.util.SparseArray;

import com.example.booksfeed.camera.GraphicOverlay;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.Detector;

public class OcrDetector implements Detector.Processor<TextBlock> {

    private GraphicOverlay<OcrGraphic> mGraphicOverlay;

    public OcrDetector(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
            mGraphicOverlay.add(graphic);
        }
    }

    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}