package `in`.divu.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.divu.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter();
                }
            }
        }
    }
}



@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    val conversion = remember { mutableDoubleStateOf(1.00) }
    val oconversion = remember { mutableDoubleStateOf(1.00) }

    fun converter(){
      //  ?: elvis operator
        val inputvaluedouble = inputValue.toDoubleOrNull() ?: 0.0

        val result = (inputvaluedouble * conversion.value *100/oconversion.value).roundToInt()/100.0

        outputValue= result.toString()


    }




    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    )

    {

        Text("Unit Converter App",
            style = MaterialTheme.typography.headlineLarge)


        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue= it
            converter()
        },
            label = { Text("Enter value")}
            )

        

        Spacer(modifier = Modifier.height(50.dp))




        Row {
            Box {
                Button(onClick = { iExpand= true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = iExpand  , onDismissRequest = { iExpand= false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpand= false;
                            inputUnit= "Centimeters"
                            conversion.value= 0.01
                            converter()
                        })

                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpand= false;
                            inputUnit= "Meters"
                            conversion.value= 1.0
                            converter()
                        })


                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpand= false;
                            inputUnit= "Feet"
                            conversion.value= 0.3048
                            converter()
                        })

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpand= false;
                            inputUnit= "Millimeters"
                            conversion.value= 0.001
                            converter()
                        })

                }
            }

            Spacer(modifier = Modifier.width(25.dp))

            Box {
                Button(onClick = { oExpand= true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")

                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand= false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpand= false
                            outputUnit="Centimeters"
                            oconversion.value=0.01
                            converter()
                        })

                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpand= false
                            outputUnit="Meters"
                            oconversion.value=1.00
                            converter()
                        })



                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpand= false
                            outputUnit="Feet"
                            oconversion.value=0.3048
                            converter()
                        })

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            oExpand= false
                            outputUnit="Millimeters"
                            oconversion.value=0.001
                            converter()
                        })

                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)


    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}