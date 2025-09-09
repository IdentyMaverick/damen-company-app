package Screens

import CalendarsResponse
import NewsResponse
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.now
import com.kizitonwose.calendar.core.plusMonths
import com.yourapp.design.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.YearMonth
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.char
import org.example.nazlican.getImagePainter
import kotlin.time.ExperimentalTime

class EventCalendar(componentContext: ComponentContext, private val onEventCalendarBack: () -> Unit, private val onFeedbackClick: () -> Unit) : ComponentContext by componentContext {
    val calendarsState = MutableValue<List<CalendarsResponse>>(emptyList())
    val format = LocalDateTime.Format {
        year(); char('-'); monthNumber(); char('-'); dayOfMonth()
        char(' ')
        hour(); char(':'); minute(); char(':'); second()
        char('.'); secondFraction(fixedLength = 1)
    }


    init {
        loadCalendars()
    }
    @Composable
    fun Render() {
        val state by calendarsState.subscribeAsState()
        for (item in state) {
            val parsed = LocalDateTime.parse(item.date, format)
            val day = parsed.date.dayOfMonth
        }
        Box(
            modifier = Modifier.Companion // Tüm ana ekran kapsıyan box.
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.Companion
                    .fillMaxSize()
            ) {
                Box( // Search Box üstünü kapsayan box.
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(AppColors.Primary)
                ) {
                    Box( // Damen logosunu ve ikonları kapsayan box. Yatay düzlemde.
                        modifier = Modifier.Companion
                            .align(Alignment.Companion.Center)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                    ) {
                        // Sol ikon
                        IconButton(
                            onClick = { onEventCalendarBack() },
                            modifier = Modifier.Companion
                                .size(45.dp)
                                .align(Alignment.Companion.CenterStart)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowLeft,
                                contentDescription = null,
                                tint = AppColors.Third,
                                modifier = Modifier.Companion
                                    .size(40.dp)
                            )
                        }
                        // Ortadaki logo
                        Image(
                            painter = getImagePainter(),
                            contentDescription = "Damen Logo",
                            modifier = Modifier.Companion
                                .size(128.dp)
                                .align(Alignment.Companion.Center)
                        )
                        // Sağdaki ikonlar
                        Row( // Sağdaki 2 ikonu ayrıca yatayda içeren row. Önemsiz.
                            modifier = Modifier.Companion.align(Alignment.Companion.CenterEnd),
                            verticalAlignment = Alignment.Companion.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onFeedbackClick() },
                                modifier = Modifier.Companion.size(40.dp)
                            ) {
                                Icon(
                                    Icons.Default.Forum,
                                    contentDescription = null,
                                    tint = Color.Companion.White
                                )
                            }
                            Spacer(Modifier.Companion.size(10.dp))
                        }
                    }
                    Box(  // SearchBox ile bölen yatay çizgi box.
                        modifier = Modifier.Companion
                            .fillMaxWidth()
                            .height(5.dp)
                            .background(color = AppColors.Third)
                            .align(Alignment.Companion.BottomCenter)
                    )
                }
                Column(
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .background(Color.Companion.Transparent)
                        .align(alignment = Alignment.Companion.CenterHorizontally)
                        .padding(top = 20.dp)
                ) {
                    Text(
                        "Etkinlik Takvimi",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Companion.ExtraBold,
                            color = AppColors.Primary
                        ),
                        modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                    )
                    Spacer(Modifier.Companion.size(20.dp))
                    Example1Page()
                    Spacer(Modifier.Companion.size(20.dp))
                    Text(
                        "Yakın Olaylar",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Companion.ExtraBold,
                            color = AppColors.Primary
                        ),
                        modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                    )
                    Spacer(Modifier.Companion.size(20.dp))
                    LazyColumn(
                        modifier = Modifier.Companion
                            .padding(top = 10.dp),
                        contentPadding = PaddingValues(horizontal = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items( state ) { item ->
                            Card(
                                modifier = Modifier
                                    .height(50.dp)
                                    .clickable(enabled = false, onClick = {}),
                                elevation = CardDefaults.cardElevation(10.dp),
                                border = BorderStroke(width = 0.dp, color = Color.Transparent),
                                colors = CardDefaults.cardColors(containerColor = AppColors.Secondary)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.Center)
                                            .padding(start = 20.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Row() {
                                            Text(
                                                item.title,
                                                style = TextStyle(
                                                    color = AppColors.Primary,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 15.sp
                                                ),
                                            )
                                            Text(
                                                item.date,
                                                style = TextStyle(
                                                    color = AppColors.Primary,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 15.sp
                                                ),
                                            )
                                        }

                                    }
                                }
                            }
                            Spacer(Modifier.Companion.size(20.dp))
                        }
                    }
                }
            }
        }
    }
    @OptIn(ExperimentalTime::class)
    @Composable
    fun Example1Page(adjacentMonths: Int = 500) {
        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(adjacentMonths) }
        val endMonth = remember { currentMonth.plusMonths(adjacentMonths) }
        val selections = remember { mutableStateListOf<CalendarDay>() }
        val daysOfWeek = remember { daysOfWeek() }
        Column(
            modifier = Modifier
                .height(320.dp)
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 10.dp) ) {
            val state = rememberCalendarState(
                startMonth = startMonth,
                endMonth = endMonth,
                firstVisibleMonth = currentMonth,
                firstDayOfWeek = daysOfWeek.first(),
            )
            val coroutineScope = rememberCoroutineScope()
            /*val visibleMonth = rememberFirstMostVisibleMonth(state, viewportPercent = 90f)
            SimpleCalendarTitle(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 8.dp),
                currentMonth = visibleMonth.yearMonth,
                goToPrevious = {
                    coroutineScope.launch {
                        state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.previous)
                    }
                },
                goToNext = {
                    coroutineScope.launch {
                        state.animateScrollToMonth(state.firstVisibleMonth.yearMonth.next)
                    }
                },
            )*/
            HorizontalCalendar(
                modifier = Modifier.testTag("Calendar"),
                state = state,
                dayContent = { day ->
                    Day(day, isSelected = selections.contains(day)) { clicked ->
                        if (selections.contains(clicked)) {
                            selections.remove(clicked)
                        } else {
                            selections.add(clicked)
                        }
                    }
                },
                monthHeader = {
                    MonthHeader(daysOfWeek = daysOfWeek)
                },
            )
        }
    }

    @Composable
    private fun MonthHeader(daysOfWeek: List<DayOfWeek>) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MonthHeader"),
        ) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 8.sp,
                    text = dayOfWeek.toString(),
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }

    @Composable
    private fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
        Box(
            modifier = Modifier
                .aspectRatio(1f) // This is important for square-sizing!
                .testTag("MonthDay")
                .padding(6.dp)
                .clip(CircleShape)
                .background(color = if (isSelected) AppColors.Primary else Color.Transparent)
                .clickable(
                    enabled = false /*day.position == DayPosition.MonthDate*/,
                    onClick = { onClick(day) },
                ),
            contentAlignment = Alignment.Center,
        ) {
            val textColor = when (day.position) {
                // Color.Unspecified will use the default text color from the current theme
                DayPosition.MonthDate -> if (isSelected) Color.White else Color.Unspecified
                DayPosition.InDate, DayPosition.OutDate -> AppColors.Third
            }
            Text(
                text = day.date.dayOfMonth.toString(),
                color = textColor,
                fontSize = 14.sp,
            )
        }
    }
    private fun loadCalendars() {
        CoroutineScope(Dispatchers.IO).launch {
            val calendars = ApiService.calendars()
            calendarsState.value = calendars
        }
    }
}