package com.taio.taio.ui.screen

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.taio.taio.R
import com.taio.taio.data.CreateState
import com.taio.taio.ui.theme.Green500
import com.taio.taio.viewmodel.CreateSignatureViewModel
import java.util.*

@Composable
fun CreateSignatureScreen(navController: NavController, viewModel: CreateSignatureViewModel = viewModel()){
    val createState = viewModel.createState.collectAsState().value
    val page = remember {
        mutableStateOf(1)
    }
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = true) {
        if(page.value == 1) {
            page.value = 1
        }else{
            page.value = page.value - 1
        }
    }

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            viewModel.onDocDate("$mYear/${mMonth+1}/$mDayOfMonth")
        }, mYear, mMonth, mDay
    )

    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Buat Tanda Tangan",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        when(page.value){
            1 -> PageOne(
                viewModel = viewModel,
                createState = createState,
                page = page,
                focusManager = focusManager,
                datePickerDialog = mDatePickerDialog,
            )
            2 -> PageTwo(
                viewModel = viewModel,
                createState = createState,
                page = page,
                focusManager = focusManager,
            )
        }
    }
}


@Composable
fun PageOne(
    viewModel: CreateSignatureViewModel,
    createState: CreateState,
    page: MutableState<Int>,
    focusManager: FocusManager,
    datePickerDialog: DatePickerDialog,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(top = 10.dp),
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        SmallTextField(
            label = "Judul/Nama Dokumen*",
            placeholder = "Masukkan Judul/Nama Dokumen",
            text = createState.documentName,
            onValueChange = {documentName -> viewModel.onDocName(documentName)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        DateField(
            label = "Tanggal Dokumen",
            text = createState.documentDate,
            onValueChange = {documentDate -> viewModel.onDocDate(documentDate)},
            onViewClick = { datePickerDialog.show() },
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            placeholder = "Tanggal(yyyy/mm/dd)",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        SmallTextField(
            label = "Nomor Dokumen (Optional)",
            placeholder = "Masukkan Nomor Dokumen",
            text = createState.documentNumber,
            onValueChange = {documentNumber -> viewModel.onDocNumber(documentNumber)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        LargeTextField(
            label = "Deskripsi Dokumen*",
            placeholder = "Uraikan keterangan dokumen yang ditandatangani...",
            text = createState.documentDescription,
            onValueChange = {documentDescription -> viewModel.onDocDesc(documentDescription)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(80.dp))
        Spacer(Modifier.weight(1f))
        ButtonFooter(
            label = "Selanjutnya",
            onButtonClick = {
                if(!viewModel.isPageOneValid()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 2
                }
            }
        )
        Spacer(Modifier.size(80.dp))
    }
}

@Composable
fun PageTwo(
    viewModel: CreateSignatureViewModel,
    createState: CreateState,
    page: MutableState<Int>,
    focusManager: FocusManager
){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
        .padding(top = 10.dp)) {

        Text(
            text = "Dokumen Preview",
            fontSize = 15.sp,
            color = Color.Black
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(120.dp)
            .border(2.dp, Green500, RoundedCornerShape(10.dp))) {
            Button(
                modifier = Modifier.padding(start = 84.dp, top = 32.dp),
                colors = ButtonDefaults.buttonColors(Green500),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Upload Dokumen")
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        ButtonFooter(
            label = "Simpan",
            onButtonClick = {
            }
        )
        ButtonFooterWhite(
            label = "Kembali",
            onButtonClick = {page.value = 1})
    }
}


@Composable
fun ButtonFooter(
    label: String,
    onButtonClick: () -> Unit
){
    Button(
        onClick = {
            onButtonClick()
        },
        content = {
            Text(text = label, color = Color.White)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF27A74A))
    )
}


/*Fun Untuk SmallTextField*/
@Composable
fun SmallTextField(
    label: String,
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){

    Text(
        text = label,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}

/*Fun Field Date*/
@Composable
fun DateField(
    label: String,
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    onViewClick: () -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){
    Text(
        text = label,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        ),
        trailingIcon = {
            IconButton(onClick = {
                onViewClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = stringResource(R.string.content_description_visibility),
                    tint = Green500,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

/*Fun Untuk LargeTextField*/
@Composable
fun LargeTextField(
    label: String,
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){

    Text(
        text = label,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxSize()
            .height(190.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}


@Composable
fun ButtonFooterWhite(
    label: String,
    onButtonClick: () -> Unit
){
    Button(
        onClick = {
            onButtonClick()
        },
        content = {
            Text(text = label, color = Black)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White)
    )
}

@Composable
fun ButtonGroupFooter(){
    Column(modifier=Modifier.padding(16.dp)) {
        ButtonFooter(label = "Simpan"){}
        Spacer(modifier = Modifier.size(10.dp))
        ButtonFooterWhite(label = "Kembali") {}
    }
}



