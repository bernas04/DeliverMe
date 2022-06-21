import React, { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button, Alert } from 'react-native';
import { useFocusEffect } from '@react-navigation/native';
import * as SecureStore from 'expo-secure-store';
import AsyncStorage from '@react-native-async-storage/async-storage';

// -- SecureStore usage:
// -- Saving items:
// await SecureStore.setItemAsync(key, value);
// -- getting items:
// let result = await SecureStore.getItemAsync(key);
// -- note: supposedly does not work in web


// temporary fix for development, remove in production
const process = {env: {REACT_APP_API_URL: "http://localhost:8081/api"}}

const RegisterScreen = ({viewSetter}) => {

    const [processing, setProcessing] = useState(false)

    const [name, setName] = useState("");
    const [birthdate, setBirthdate] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confPassword, setConfPassword] = useState("")

    const submit = () => {
        if (name === "" || birthdate === "" || username === "" || password === "" || confPassword === "") {
            Alert.alert("Please fill in all fields.")
            return
        }
        if (password != confPassword) {
            Alert.alert("Passwords don't match!")
            return
        }

        setProcessing(true)
        fetch(`${process.env.REACT_APP_API_URL}/auth/registerRider`, {
            method: "POST",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                name: name,
                birthDate: birthdate,
                username: username,
                password: password
            })
        })
        .then((response) => {
            if (response.ok) {
                viewSetter("login")
            }
            else {
                Alert.alert("Registration could not be completed")
            }
        })
        .catch(() => {
            Alert.alert("A communication error ocurred")
            setProcessing(false)
        })
    }


    return(
        <View style={styles.container}>
            <Text style={styles.title}>Register</Text>

            <Text style={styles.label}>Name:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setName}
                value={name}
                placeholder="Name"
                autoCorrect={false}
                maxLength={20}
                autoComplete={'name'}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Date of Birth:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setBirthdate}
                value={birthdate}
                placeholder="Birthdate"
                autoCorrect={false}
                maxLength={10}
                autoComplete={'birthdate-full'}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Username:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setUsername}
                value={username}
                placeholder="Username"
                autoCorrect={false}
                maxLength={15}
                autoComplete={'username'}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Password:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setPassword}
                value={password}
                placeholder="Password"
                autoCorrect={false}
                maxLength={15}
                secureTextEntry={true}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Confirm Password:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setConfPassword}
                value={confPassword}
                placeholder="Confirm Password"
                autoCorrect={false}
                maxLength={15}
                secureTextEntry={true}
            />
            <View style={styles.separator} />
            <View style={styles.separator} />

            <Button title='Submit' onPress={submit} disabled={processing} />
        </View>
    )
}

const LoginScreen = () => {

    const [currentUser, setCurrentUser] = useState(null)

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [processing, setProcessing] = useState(false);

    const submit = () => {
        setProcessing(true)
        fetch(`${process.env.REACT_APP_API_URL}/auth/authenticate`, {
            method: "POST",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
        .then((response) => {
            if (response.ok) {
                let data = response.json()
                data.then((data) => {
                        //SecureStore.setItemAsync("current_user", JSON.stringify(data))
                        AsyncStorage.setItem("current_user", JSON.stringify(data))
                        setCurrentUser(data)
                        setProcessing(false)
                    }
                )  
            }
            else {
                Alert.alert("Incorrect credentials")
                setProcessing(false)
            }
        })
        .catch(() => {
            Alert.alert("A communication error ocurred")
            setProcessing(false)
        })
    }

    const logout = () => {
        setProcessing(true)
        //SecureStore.deleteItemAsync("current_user")
        AsyncStorage.removeItem("current_user")
        setCurrentUser(null)
        setProcessing(false)
    }



    useFocusEffect(
        React.useCallback(() => {
            async function getCurrentUser() {
                //let currUser = await SecureStore.getItemAsync("current_user")
                //setCurrentUser(JSON.parse(currUser))

                let currUser = await AsyncStorage.getItem("current_user")
                setCurrentUser(currUser ? JSON.parse(currUser) : null)
            }
            
            getCurrentUser()
        }, [])
    );


    return(
        currentUser != null ?
        <View style={styles.container}>
            <Text style={styles.title}>Currently logged in as:</Text>
            <Text style={styles.title}>{currentUser.name}</Text>
            <View style={styles.separator} />
            <Button title='Logout' onPress={logout} disabled={processing} />
        </View>
        :
        <View style={styles.container}>
            <Text style={styles.title}>Login</Text>

            <Text style={styles.label}>Username:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setUsername}
                value={username}
                placeholder="Username"
                autoCorrect={false}
                maxLength={15}
                autoComplete={'username'}
            />
            <View style={styles.separator} />

            <Text style={styles.label}>Password:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setPassword}
                value={password}
                placeholder="Password"
                autoCorrect={false}
                maxLength={15}
                secureTextEntry={true}
            />
            <View style={styles.separator} />
            <View style={styles.separator} />

            <Button title='Submit' onPress={submit} disabled={processing} />
        </View>
    )
}

const ProfileScreen = ({navigation}) => {

    const [currScreen, setCurrScreen] = useState("login")  // "login" or "register"

    const switchView = () => {
        setCurrScreen(currScreen === "login" ? "register" : "login")
    }

    return(
        <View style={styles.outerContainer}>
            {
                currScreen === "login" ?
                <LoginScreen />
                :
                <RegisterScreen viewSetter={setCurrScreen} />
            }
            <Button
                style={styles.viewSwitchButton}
                title={ currScreen === "login" ? "Don't have an account? Register" : "Already have an account? Login" }
                onPress={switchView}
            />
        </View>
    )

}

const styles = StyleSheet.create({
    title: {
        fontSize: 24,
        marginBottom: 18,
        textAlign: "center"
    },
    container: {
        flex: 1,
        padding: 16,
        paddingTop: 48,
        backgroundColor: '#fff',
        justifyContent: 'flex-start'
    },
    input: {
        fontSize: 18,
        borderColor: "#555",
        borderRadius: 4,
        borderWidth: 1,
        padding: 4
    },
    label: {
        fontSize: 18
    },
    separator: {
        marginVertical: 6
    },
    viewSwitchButton: {
        alignSelf: "flex-end"
    },
    outerContainer: {
        flex: 1
    }
});

export default ProfileScreen;