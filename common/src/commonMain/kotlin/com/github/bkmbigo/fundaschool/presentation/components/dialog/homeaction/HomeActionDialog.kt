package com.github.bkmbigo.fundaschool.presentation.components.dialog.homeaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.di.WithKoin
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.UserRepository
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LoginDialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.login.LogoutDialog
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.seiko.imageloader.rememberAsyncImagePainter
import dev.gitlive.firebase.auth.FirebaseUser

@Composable
fun HomeActionDialog(
    onAction: (HomeActionDialogAction) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    val authRepository = remember { withKoin<AuthRepository>() }
    val userRepository = remember { withKoin<UserRepository>() }

    val user = authRepository.currentUser()
    var userIsAdmin by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        userIsAdmin = user?.let { loggedInUser ->
            userRepository.getUser(loggedInUser.uid)?.isAdmin ?: false
        } ?: false
    }

    // Action Dialog
    DialogLayout(onDismissRequest = onDismissRequest) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.elevatedCardElevation(8.dp)
        ) {
            user?.let {
                LoggedInContainer(
                    user,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    onEditProfile = { onAction(HomeActionDialogAction.EditProfile) },
                    onLogout = { onAction(HomeActionDialogAction.Logout) },
                )
            } ?: ContainerAction(
                icon = Icons.Default.Login,
                title = "Login",
                onAction = { onAction(HomeActionDialogAction.Login) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        if (userIsAdmin) {
            ContainerAction(
                icon = Icons.Default.SupervisorAccount,
                title = "Admin Panel",
                onAction = { onAction(HomeActionDialogAction.NavigateToAdmin) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
        }

        ContainerAction(
            icon = Icons.Default.Money,
            title = "Donations",
            onAction = { onAction(HomeActionDialogAction.NavigateToDonation) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        ContainerAction(
            icon = Icons.Default.WorkHistory,
            title = "Projects",
            onAction = { onAction(HomeActionDialogAction.NavigateToProjects) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        ContainerAction(
            icon = Icons.Default.Group,
            title = "About Us",
            onAction = { onAction(HomeActionDialogAction.NavigateToAboutUs) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

    }
}


@Composable
private fun LoggedInContainer(
    user: FirebaseUser,
    modifier: Modifier = Modifier,
    onEditProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            user.photoURL?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Inside
                )
            } ?: Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Inside
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = user.displayName ?: "",
                    style = layoutProperties.TextStyle.textLayoutHelper
                )

                Text(
                    text = user.email ?: "",
                    style = layoutProperties.TextStyle.bodyText
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        ContainerAction(
            icon = Icons.Default.AccountCircle,
            title = "Edit Profile",
            onAction = onEditProfile,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        ContainerAction(
            icon = Icons.Default.Logout,
            title = "Logout",
            onAction = onLogout,
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
    }
}

@Composable
private fun ContainerAction(
    icon: ImageVector,
    title: String,
    onAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    Row(
        modifier = modifier.clickable { onAction() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            style = layoutProperties.TextStyle.dialogAction,
            modifier = Modifier.weight(1f, true)
        )

    }
}