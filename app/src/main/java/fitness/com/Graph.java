package fitness.com;

import android.content.Context;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
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

    private Double tg = 50.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        List<Datas> datasList = readDataFromCsv();
        graphView_Weight = findViewById(R.id.idGraphView_Weight);
        graphView_BMI = findViewById(R.id.idGraphView_BMI);

        // Llenar listas con datos
        for (Datas data : datasList) {
            dateList.add(data.getLocalData());
            weightList.add(Double.parseDouble(data.getWeight()));
            bmiList.add(Double.parseDouble(data.getBmi()));
        }

        createGraph_with_target(getApplicationContext(),graphView_Weight, dateList, weightList,"Weight", tg);
        createGraph(getApplicationContext(),graphView_BMI, dateList,bmiList, "BMI");
//---------------------------------------------------------------------------------------------------
    }

    public static void createGraph(Context context, GraphView graphView,
                                   List<String> dateList, List<Double> infoList, String graphTitle) {

        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];
        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            // Crear un objeto DataPoint con fecha y valor de peso
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }

        // Crear una serie de puntos para la gráfica
        PointsGraphSeries<DataPoint> pointsSeries = new PointsGraphSeries<>(dataPointsArray);
        pointsSeries.setColor(context.getResources().getColor(R.color.progress_red));

        // Crear una serie de líneas para la gráfica
        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(dataPointsArray);
        lineSeries.setColor(context.getResources().getColor(R.color.white));

        // Configurar propiedades de la gráfica
        graphView.setTitle(graphTitle);
        graphView.setTitleColor(context.getResources().getColor(R.color.white));
        graphView.setTitleTextSize(60);

        // Agregar ambas series a la gráfica
        graphView.addSeries(pointsSeries);
        graphView.addSeries(lineSeries);

        // Configurar etiquetas personalizadas en el eje x
        String[] dateLabels = dateList.toArray(new String[0]);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(dateLabels.length);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // Convertir el valor de double a índice entero para acceder al array
                    int index = (int) value;
                    // Verificar si el índice está dentro del rango del array
                    if (index >= 0 && index < dateLabels.length) {
                        // Devolver la etiqueta personalizada para la posición
                        return dateLabels[index];
                    } else {
                        // Devolver una cadena vacía si el índice está fuera de rango
                        return "";
                    }
                } else {
                    // Devolver el valor y normalmente para el eje y
                    return super.formatLabel(value, isValueX);
                }
            }
        });
        graphView.getGridLabelRenderer().setHorizontalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(context.getResources().getColor(R.color.white));

        // Configurar líneas discontinuas
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.progress_red));
        paint.setStrokeWidth(8); // Ajusta el grosor de la línea
        paint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0)); // Establece el patrón de línea discontinua
        lineSeries.setCustomPaint(paint);


    }

    public static void createGraph_with_target(Context context, GraphView graphView,
                                               List<String> dateList, List<Double> infoList,
                                               String graphTitle, double targetValue) {

        DataPoint[] dataPointsArray = new DataPoint[infoList.size()];
        for (int i = 0; i < infoList.size(); i++) {
            double infoValue = infoList.get(i);
            // Create a DataPoint object with date and weight value
            dataPointsArray[i] = new DataPoint(i, infoValue);
        }

        // Create a series of points for the graph
        PointsGraphSeries<DataPoint> pointsSeries = new PointsGraphSeries<>(dataPointsArray);
        pointsSeries.setColor(context.getResources().getColor(R.color.progress_red));

        // Create a series of lines for the graph
        LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(dataPointsArray);
        lineSeries.setColor(context.getResources().getColor(R.color.white));

        // Add both series to the graph
        graphView.addSeries(pointsSeries);
        graphView.addSeries(lineSeries);

        // Configure graph properties
        graphView.setTitle(graphTitle);
        graphView.setTitleColor(context.getResources().getColor(R.color.white));
        graphView.setTitleTextSize(60);

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
        graphView.getGridLabelRenderer().setHorizontalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalAxisTitleColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(context.getResources().getColor(R.color.white));
        graphView.getGridLabelRenderer().setVerticalLabelsColor(context.getResources().getColor(R.color.white));

        // Configure dashed lines for the main graph
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.progress_red));
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