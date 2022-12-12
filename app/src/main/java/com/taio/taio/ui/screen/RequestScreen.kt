package com.taio.taio.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.theme.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.ui.theme.TandatanganioMobileTheme

@Composable
fun RequestScreen(
    authenticatedUser: User,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray100)
            .padding(16.dp)
    ) {
        Text(
            text = "Permintaan",
            fontSize = 26.sp,
            fontFamily = fonts,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 10.dp)
                .padding(horizontal = 3.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
            {
                Box(
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.2f)
                )
                {
                    Image(
                        modifier = modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .align(Alignment.TopCenter),
                        contentDescription = "user image",
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = authenticatedUser.avatar)
                    )

                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                )
                {
                    Column(
                        modifier = modifier.height(50.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Surat Keterangan Mahasiswa",
                            fontFamily = fonts,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )

                        Text(
                            text = "Ahmad Luhur Pakerti",
                            fontFamily = fonts,
                            fontSize = 15.sp,
                            color = Text01
                        )
                    }
                    Column() {
                        Text(
                            text = "”Lorem ipsum dolor sit amet consectetur. Libero non eget sit egestas morbi”",
                            modifier = modifier.padding(top = 5.dp),
                            fontFamily = fonts,
                            fontSize = 15.sp,
                            color = Text01
                        )
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            border = BorderStroke(1.dp, Green500),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Image(
                                painterResource(id = R.drawable.document),
                                contentDescription = null,
                            )
                            Text(
                                text = stringResource(R.string.document_preview),
                                color = Purple200,
                                modifier = Modifier
                                    .padding(start = 5.dp)
                            )
                        }
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(end = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(7.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                                )
                                {
                                    Text(
                                        text = "Tolak",
                                        fontFamily = fonts,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }

                                Button(
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(7.dp),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
                                )
                                {
                                    Text(
                                        text = "Terima",
                                        fontFamily = fonts,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center,
                                        color = Color.White
                                    )
                                }
                            }

                        }
                    }

                }
            }
        }
    }

}


@Composable
fun RequestLongList(requestList: List<UserRequest>) {
    LazyColumn(modifier = Modifier) {
        items(items = requestList) { request ->
            Request(request = request)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RequestScreenPreview() {
    TandatanganioMobileTheme {
        val mockUser = User(R.drawable.avatar, "Asep Konco")
        RequestScreen(mockUser)
    }
}