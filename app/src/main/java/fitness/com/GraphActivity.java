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

public class GraphActivity extends AppCompatActivity {

    // initializing the GraphView
    private GraphView graphView_Weight;
    private GraphView graphView_BMI;

    // initializing the list of data to be plotted
    private List<String> dateList = new ArrayList<>();
    private List<Double> weightList = new ArrayList<>();
    private List<Double> bmiList = new ArrayList<>();

    // variable for the target weight value
    float tg_int;
    double tg;

    // initializing sharedpreference object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        try {
            // creating the dataslist by reading the csv file
            List<Datas> datasList = readDataFromCsv();

            // finding graphView by id
            graphView_Weight = findViewById(R.id.idGraphView_Weight);
            graphView_BMI = findViewById(R.id.idGraphView_BMI);

            // adding the data to each list
            for (Datas data : datasList) {
                dateList.add(data.getLocalData());
                weightList.add(Double.parseDouble(data.getWeight()));
                bmiList.add(Double.parseDouble(data.getBmi()));
            }

            // getting tg from preferences
            sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            tg_int = sharedPreferences.getFloat(ValueState, 0);
            // converting tg from float to double
            tg = (double) tg_int;

            // creating the weigth graph
            createWeightGraph(getApplicationContext(),graphView_Weight, dateList, weightList, tg_int);
            // creating the BMI graph
            createBmiGraph(getApplicationContext(),graphView_BMI, dateList, bmiList);

        }
        catch (IllegalArgumentException exception) // if no data are already registered
        {
            Toast.makeText(getApplicationContext(),"You must calculate your BMI first",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public static void createBmiGraph(Context context, GraphView graphView,
                                   List<String> dateList, List<Double> infoList) {

        // Using a DataPoint array to store easily the x-y variable
        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];

        // Create a DataPoint object with date and weight value
        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }

        /*
        // making the graph scalable and scrollable
        // leave the comment here, it can be put in further implementation
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

        // Add both series to the graph
        graphView.addSeries(pointsSeries);
        graphView.addSeries(lineSeries);

        // Configure custom labels on the x-axis
        // converting the dateList in array of string data
        String[] dateLabels = dateList.toArray(new String[0]);
        // setting the length of grid label x-axis
        graphView.getGridLabelRenderer().setNumHorizontalLabels(dateLabels.length);
        // changing the actual format of the label
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

        // configure axis labels
        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(context.getResources().getColor(R.color.white));

        // Configure dashed lines
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.light_blue));
        paint.setStrokeWidth(8); // Adjust the line thickness
        // Set the dashed line pattern 10 px line, 10 px space, 0 is the initial pahse
        paint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));
        // assigning the paint object to the line series
        lineSeries.setCustomPaint(paint);
    }

    public static void createWeightGraph(Context context, GraphView graphView,
                                               List<String> dateList, List<Double> infoList, double targetValue) {

        // Using a DataPoint array to store easily the x-y variable
        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];

        // Create a DataPoint object with date and weight value
        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }


        /*
        // making the graph scalable and scrollable
        // leave the comment here, it can be put in further implementation
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

        // Configure axis and label colors
        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(90);
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

    // reading data from csv function
    private List<Datas> readDataFromCsv() {
        // Initialize a list to store Datas objects
        List<Datas> datasList = new ArrayList<>();

        // Get the path to the external files directory, null is for searching the main directory
        File externalFilesDir = getExternalFilesDir(null);

        // Create the full path for the CSV file
        String csvFilePath = externalFilesDir.getAbsolutePath() + "/data.csv";

        // try with resources close reader after the execution of try block
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true;

            // Read each line from the CSV file
            while ((line = reader.readLine()) != null) {
                // Skip the first line (header)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Split the line into parts using comma as a delimiter
                String[] parts = line.split(",");

                // Remove any quotes from the parts and extract data, weight, and bmi
                String data = parts[0].replace("\"", "");
                String weight = parts[1].replace("\"", "");
                String bmi = parts[2].replace("\"", "");

                // Create a new Datas object and add it to the list
                datasList.add(new Datas(data, weight, bmi));
            }
        } catch (IOException | NumberFormatException e) { // catch different Exceptions
            // Print the stack trace for IOException and NumberFormatException
            e.printStackTrace();
        }

        // Return the list of Datas objects
        return datasList;
    }

    // THIS WAS JUST TO SET THE CSV FOR TESTING
    public void writeDataToCsv(String data, String weight, String bmi) {
        try {
            File file = new File(getExternalFilesDir(null), "data.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\"" + data + "\",\"" + weight + "\",\"" + bmi + "\"");
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            // printing in the standard error logcat the stack trace of the exception thrown
            e.printStackTrace();
        }
        // leave the function here, it may be useful in further implementation, like for
        // clearing the csv data
    }
}