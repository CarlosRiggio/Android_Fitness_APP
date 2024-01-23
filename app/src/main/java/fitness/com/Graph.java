package fitness.com;

import static fitness.com.BMIActivity.MyPREFERENCES;
import static fitness.com.SetTargetActivity.ValueState;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {

    private GraphView graphView_Weight;
    private GraphView graphView_BMI;
    private List<String> dateList = new ArrayList<>();
    private List<Double> weightList = new ArrayList<>();
    private List<Double> bmiList = new ArrayList<>();
    float tg_int;
    double tg;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        try {
            List<Datas> datasList = readDataFromCsv();
            graphView_Weight = findViewById(R.id.idGraphView_Weight);
            graphView_BMI = findViewById(R.id.idGraphView_BMI);

            // Llenar listas con datos
            for (Datas data : datasList) {
                dateList.add(data.getLocalData());
                weightList.add(Double.parseDouble(data.getWeight()));
                bmiList.add(Double.parseDouble(data.getBmi()));
            }

            // getting tg from preferences
            sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            tg_int = sharedPreferences.getFloat(ValueState, 0);
            tg = (double) tg_int;

            createGraph_with_target(getApplicationContext(),graphView_Weight, dateList, weightList, tg_int);
            createGraph(getApplicationContext(),graphView_BMI, dateList, bmiList);

        }
        catch (IllegalArgumentException exception)
        {
            Toast.makeText(getApplicationContext(),"You must calculate your BMI first",Toast.LENGTH_SHORT).show();
            finish();
        }

//---------------------------------------------------------------------------------------------------
    }

    public static void createGraph(Context context, GraphView graphView,
                                   List<String> dateList, List<Double> infoList) {

        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];
        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            // Create a DataPoint object with date and weight value
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }

        /*
        // making the graph scalable and scrollable
        Viewport viewport = graphView.getViewport();
        viewport.setScalable(true);
        viewport.setScrollable(true);
        viewport.setScalableY(true);
        viewport.setScrollableY(true);
        */

        // Create a series of points for the graph
        PointsGraphSeries<DataPoint> pointsSeries = new PointsGraphSeries<>(dataPointsArray);
        pointsSeries.setColor(context.getResources().getColor(R.color.lift_lab_blue));

        // Create a series of lines for the graph
        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(dataPointsArray);
        lineSeries.setColor(context.getResources().getColor(R.color.light_blue));

        // Configure graph properties
        graphView.setTitleColor(context.getResources().getColor(R.color.white));
        graphView.setTitleTextSize(60);

        // Add both series to the graph
        graphView.addSeries(pointsSeries);
        graphView.addSeries(lineSeries);

        // Configure custom labels on the x-axis
        String[] dateLabels = dateList.toArray(new String[0]);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(dateLabels.length);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // Convert double value to integer index to access the array
                    int index = (int) value;
                    // Check if the index is within the array range
                    if (index >= 0 && index < dateLabels.length) {
                        // Return the custom label for the position
                        return dateLabels[index];
                    } else {
                        // Return an empty string if the index is out of range
                        return "";
                    }
                } else {
                    // Return the value as usual for the y-axis
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        graphView.getGridLabelRenderer().setHorizontalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(context.getResources().getColor(R.color.white));

        // Configure dashed lines
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStrokeWidth(8); // Adjust the line thickness
        paint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0)); // Set the dashed line pattern
        lineSeries.setCustomPaint(paint);
    }


    public static void createGraph_with_target(Context context, GraphView graphView,
                                               List<String> dateList, List<Double> infoList, double targetValue) {

        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];

        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            // Create a DataPoint object with date and weight value
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }


        /*
        // making the graph scalable and scrollable
        Viewport viewport = graphView.getViewport();
        viewport.setScalable(true);
        viewport.setScrollable(true);
        viewport.setScalableY(true);
        viewport.setScrollableY(true);
        */

        // Create a series of points for the graph
        PointsGraphSeries<DataPoint> pointsSeries = new PointsGraphSeries<>(dataPointsArray);
        pointsSeries.setColor(context.getResources().getColor(R.color.lift_lab_blue));

        // Create a series of lines for the graph
        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(dataPointsArray);
        lineSeries.setColor(context.getResources().getColor(R.color.light_blue));

        // Add both series to the graph
        graphView.addSeries(pointsSeries);
        graphView.addSeries(lineSeries);

        // Add horizontal line for the target value
        LineGraphSeries<DataPoint> targetLineSeries = new LineGraphSeries<>(
                new DataPoint[]{new DataPoint(0, targetValue), new DataPoint(infoList.size() - 1, targetValue)});
        targetLineSeries.setColor(context.getResources().getColor(R.color.green_1));
        graphView.addSeries(targetLineSeries);

        // Configure custom labels on the x-axis
        String[] dateLabels = dateList.toArray(new String[0]);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(dateLabels.length);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {

                if (isValueX) {
                    int index = (int) value;
                    if (index >= 0 && index < dateLabels.length) {
                        return dateLabels[index];
                    } else {
                        return "";
                    }
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });


        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        // Configure axis and label colors
        graphView.getGridLabelRenderer().setHorizontalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(context.getResources().getColor(R.color.white));

        // Configure dashed lines for the main graph
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStrokeWidth(8);
        paint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        lineSeries.setCustomPaint(paint);
    }

    private List<Datas> readDataFromCsv() {
        List<Datas> datasList = new ArrayList<>();
        File externalFilesDir = getExternalFilesDir(null);
        String csvFilePath = externalFilesDir.getAbsolutePath() + "/data.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                String data = parts[0].replace("\"", "");
                String weight = parts[1].replace("\"", "");
                String bmi = parts[2].replace("\"", "");
                datasList.add(new Datas(data, weight, bmi));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return datasList;
    }

    public void writeDataToCsv(String data, String weight, String bmi) {
        try {
            File file = new File(getExternalFilesDir(null), "data.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\"" + data + "\",\"" + weight + "\",\"" + bmi + "\"");
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // THIS WAS JUST TO SET THE CSV FOR TESTING
    }



}