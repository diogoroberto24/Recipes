package com.project.recipes.screens

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.recipes.R
import com.project.recipes.model.User
import com.project.recipes.navigation.Destination
import com.project.recipes.repository.SharedPreferencesUserRepository
import com.project.recipes.ui.theme.RecipesTheme

@Composable
fun SignupScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ){
        TopEndCard(modifier = Modifier.align(Alignment.TopEnd))
        BottonStartCard(modifier = Modifier.align(Alignment.BottomStart))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleComponent()
            Spacer(modifier = Modifier.height(48.dp))
            UserImage()
            SignupUserForm(navController)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun SignupScreenPreview() {
    RecipesTheme() {
        SignupScreen(rememberNavController())
    }
}
@Composable
fun TitleComponent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.create_a_new_account),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun TitleComponentPreview() {
    RecipesTheme() {
        TitleComponent()
    }
}

@Composable
fun UserImage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(120.dp)
    ){
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = stringResource(R.string.user_image),
            modifier = Modifier
                .size(100.dp)
                .align(alignment = Alignment.Center)
        )
        Icon(
            imageVector = Icons.Default.PhotoCamera,
            contentDescription = stringResource(R.string.camera_icon),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun UserImagePreview() {
    RecipesTheme() {
        UserImage()
    }
}

@Composable
fun SignupUserForm(navController: NavController) {

    // Variáveis de estado para controlar os valores exibidos nos OutlinedTextFields

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    //Variáveis de estado para verificar se os dados estão corretos
    var isNameError by remember {
        mutableStateOf(false)
    }
    var isEmailError by remember {
        mutableStateOf(false)
    }
    var isPasswordError by remember {
        mutableStateOf(false)
    }

    //Variáveis de estado para controlar a exibição da mensagem de erro
    var showDialogError by remember {
        mutableStateOf(false)
    }
    var showDialogSuccess by remember {
        mutableStateOf(false)
    }

    //Funlção para verificar se os dados estão corretos
    fun validate (): Boolean{
        isNameError = name.length < 3
        isEmailError = email.length < 3 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = name.length < 3
        return !isNameError && !isPasswordError && !isEmailError
    }

    //Criar uma instância do SharedPreferencesUserRepository
    val userRepository = SharedPreferencesUserRepository(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        // Caixa de texto para nome de usuário
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.your_name),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.person_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            isError = isNameError,
            trailingIcon = {
                if (isNameError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isNameError){
                    Text(
                        text = "O nome deve ter pelo menos 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        // Caixa de texto para e-mail do usuário
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.your_e_mail),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = stringResource(R.string.email_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            isError = isEmailError,
            trailingIcon = {
                if (isEmailError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isEmailError){
                    Text(
                        text = "O e-mail deve ter pelo menos 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        // Caixa de texto para senha do usuário
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.your_password),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.password_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            isError = isPasswordError,
            trailingIcon = {
                if (isPasswordError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isPasswordError){
                    Text(
                        text = "A senha deve ter pelo menos 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )

        // Botão Create Account
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (validate()){
                    userRepository.saveUser(
                        User(
                            name = name,
                            email = email,
                            password = password
                        )
                    )
                    showDialogSuccess = true
                } else {
                    showDialogError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.create_account),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
    //Caixa de diálogo de sucesso
    if (showDialogSuccess)
        AlertDialog(
            onDismissRequest = {showDialogError = false},
            title = {
                Text(
                    text = "Success"
                )
            },
            text = {
                Text(
                    text = "Conta criada com sucesso"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogSuccess = false
                        navController
                        .navigate(Destination.LoginScreen.route)
                    }
                ) {
                    Text(
                        text = "OK"
                    )
                }
            }
        )

    //Caixa de diálogo de erro
    if (showDialogError){
        AlertDialog(
            onDismissRequest = {showDialogError = false},
            title = {
                Text(
                    text = "Error"
                )
            },
            text = {
                Text(
                    text = "Por favor preencha todos os campos corretamente"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogError = false
                    }
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun SignupUserFormPreview() {
    RecipesTheme() {
        SignupUserForm(rememberNavController())
    }
}