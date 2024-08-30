import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.salihakbas.kekodprojectchallenge.databinding.ActivityMainBinding
import com.salihakbas.kekodprojectchallenge.databinding.FragmentSwitchBinding
import com.salihakbas.kekodprojectchallenge.ui.activity.MainActivity
import com.salihakbas.kekodprojectchallenge.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @Mock
    private lateinit var binding: ActivityMainBinding

    @Mock
    private lateinit var navHostFragment: NavHostFragment

    @Mock
    private lateinit var navController: NavController

    @Mock
    private lateinit var bottomNav: BottomNavigationView

    @Mock
    private lateinit var menu: Menu

    @Mock
    private lateinit var menuItem: MenuItem

    private lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        mainActivity = MainActivity()
        mainActivity.binding = binding
        `when`(binding.bottomNav).thenReturn(bottomNav)
        `when`(bottomNav.menu).thenReturn(menu)
        `when`(navHostFragment.navController).thenReturn(navController)
    }

    @Test
    fun testOnCreate() {
        mainActivity.onCreate(null)
        verify(binding).bottomNav
        verify(navHostFragment).navController
    }

    @Test
    fun testAddMenuItem() {
        `when`(menu.findItem(anyInt())).thenReturn(null)
        mainActivity.addMenuItem("Test", R.id.happinessFragment, R.drawable.ic_search)
        verify(menu).add(0, R.id.happinessFragment, menu.size(), "Test")
    }

    @Test
    fun testRemoveMenuItem() {
        `when`(menu.findItem(R.id.happinessFragment)).thenReturn(menuItem)
        mainActivity.removeMenuItem(R.id.happinessFragment)
        verify(menu).removeItem(R.id.happinessFragment)
    }

    @Test
    fun testNavigateToFragment() {
        mainActivity.navHostFragment = navHostFragment
        mainActivity.navigateToFragment(R.id.happinessFragment)
        verify(navController).navigate(R.id.happinessFragment)
    }
}