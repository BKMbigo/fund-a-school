package com.github.bkmbigo.fundaschool.presentation.components.dialog.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginDialog(
    onDismissRequest: () -> Unit,
    onCompletedLogin: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current
    val coroutineScope = rememberCoroutineScope()

    var eventJob: Job? = null

    val presenter = remember { withKoin<LoginPresenter>() }

    val state by presenter.state.collectAsState()

    var name by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var password by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var reenterPassword by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    var isPasswordHidden by remember { mutableStateOf(true) }

    var reenterPasswordError by remember{ mutableStateOf<String?>(null) }

    // Focus Requester
    val nameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val reenterPasswordFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch {
            presenter.events.collect { event ->
                eventJob?.cancel()
                eventJob = launch {
                    errorMessage = event
                    delay(5000)
                    if(isActive) {
                        errorMessage = null
                    }
                }
            }
        }
    }

    DialogLayout(
        onDismissRequest = onDismissRequest
    ) {
        if(state.state == LoginDialogState.REGISTRATION) {
            Text(
                text = "Name",
                style = layoutProperties.TextStyle.textLayoutHelper
            )

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(nameFocusRequester),
                placeholder = {
                    Text(
                        text = "Name"
                    )
                },
                supportingText = {
                    Text(
                        text = state.nameError ?: ""
                    )
                },
                isError = state.nameError != null,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardActions = KeyboardActions {
                    emailFocusRequester.requestFocus()
                }
            )
        }

        if(state.state == LoginDialogState.LOGIN || state.state == LoginDialogState.REGISTRATION) {
            Text(
                text = "Email",
                style = layoutProperties.TextStyle.textLayoutHelper
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(emailFocusRequester),
                placeholder = {
                    Text(
                        text = "Email"
                    )
                },
                supportingText = {
                    Text(
                        text = state.emailError ?: ""
                    )
                },
                isError = state.emailError != null,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                keyboardActions = KeyboardActions {
                    passwordFocusRequester.requestFocus()
                }
            )

            Spacer(modifier = Modifier.height(4.dp))
        }


        if(state.state == LoginDialogState.LOGIN || state.state == LoginDialogState.REGISTRATION) {
            Text(
                text = "Password",
                style = layoutProperties.TextStyle.textLayoutHelper
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                placeholder = {
                    Text(
                        text = "Password"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordHidden = !isPasswordHidden }
                    ) {
                        Icon(
                            imageVector = if (isPasswordHidden) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                supportingText = {
                    Text(
                        text = state.passwordError ?: ""
                    )
                },
                visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                isError = state.passwordError != null,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardActions = KeyboardActions {
                    if (state.state == LoginDialogState.LOGIN) {
                        coroutineScope.launch {
                            presenter.loginUser(email.text, password.text)
                        }
                    } else {
                        reenterPasswordFocusRequester.requestFocus()
                    }
                }
            )
        }

        if(state.state == LoginDialogState.REGISTRATION) {
            Text(
                text = "Reenter Password",
                style = layoutProperties.TextStyle.textLayoutHelper
            )

            OutlinedTextField(
                value = reenterPassword,
                onValueChange = {
                    reenterPassword = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(passwordFocusRequester),
                placeholder = {
                    Text(
                        text = "Reenter Password"
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { isPasswordHidden = !isPasswordHidden }
                    ) {
                        Icon(
                            imageVector = if(isPasswordHidden) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                },
                supportingText = {
                    Text(
                        text = reenterPasswordError ?: state.passwordError ?: ""
                    )
                },
                visualTransformation = if(isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                isError = state.passwordError != null || reenterPasswordError != null,
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                keyboardActions = KeyboardActions {
                    coroutineScope.launch {
                        if(reenterPassword.text == password.text) {
                            presenter.registerUser(
                                name.text,
                                email.text,
                                password.text
                            )
                        } else {
                            reenterPasswordError = "Passwords do not match"
                        }
                    }
                }
            )

        }

        if(state.state == LoginDialogState.LOGIN || state.state == LoginDialogState.REGISTRATION) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (state.state == LoginDialogState.LOGIN) "No Account? " else "Have an account? ",
                    style = layoutProperties.TextStyle.informationText
                )
                Text(
                    text = if (state.state == LoginDialogState.LOGIN) "Create one" else "Login",
                    textDecoration = TextDecoration.Underline,
                    fontFamily = layoutProperties.TextStyle.sectionTitle.fontFamily,
                    style = layoutProperties.TextStyle.informationText,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { presenter.changeState() }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        if(state.loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        AnimatedVisibility(
            visible = errorMessage != null,
            enter = expandVertically(
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow
                ),
                expandFrom = Alignment.Top
            ),
            exit = shrinkVertically(
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium
                ),
                shrinkTowards = Alignment.Top
            )
        ) {
            Text(
                text = errorMessage ?: "",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = layoutProperties.TextStyle.bodyEmphasis
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f, true)
            )

            if(state.state != LoginDialogState.SUCCESS) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            }

            Button(
                onClick = {
                    when (state.state){
                        LoginDialogState.LOGIN -> {
                            coroutineScope.launch {
                                presenter.loginUser(email.text, password.text)
                            }
                        }
                        LoginDialogState.REGISTRATION -> {
                            coroutineScope.launch {
                                if(reenterPassword.text == password.text) {
                                    presenter.registerUser(
                                        name.text,
                                        email.text,
                                        password.text
                                    )
                                } else {
                                    reenterPasswordError = "Passwords do not match"
                                }
                            }
                        }
                        LoginDialogState.SUCCESS -> {
                            onCompletedLogin()
                        }
                    }
                }
            ) {
                Text(
                    text = when(state.state){
                        LoginDialogState.LOGIN -> "Login"
                        LoginDialogState.REGISTRATION -> "Registration"
                        LoginDialogState.SUCCESS -> "Continue"
                    }
                )
            }
        }
    }
}