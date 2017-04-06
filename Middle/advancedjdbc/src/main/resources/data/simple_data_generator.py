# -*- coding: utf-8 -*-

from elizabeth import Personal
from elizabeth import Datetime
from elizabeth import Text
import string
import re
import random
import os

person = Personal('en')
datetime = Datetime('en')
data = Text('en')

POST_ID = 0
USER_ID = 0
DATE_FORMAT = "%Y-%m-%d"
def toCsvString(*values):
    return ','.join(tuple(map(lambda x: str(x), values))) + '\n'

def generatePosts(userId, n=random.randint(1,10)):
    regex = re.compile('[%s]' % re.escape(string.punctuation))

    for p in range(n):
        text = data.sentence() 
        text = regex.sub(' ', text)
        date = datetime.date(start=2010, end=2012, fmt=DATE_FORMAT) + " " + datetime.time()
        global POST_ID
        postId = POST_ID
        POST_ID += 1
        yield toCsvString(str(postId), userId, text, date)
    
def generateFriendships(n=random.randint(1,10)):
    global USER_ID
    for p in range(n):
        userId1 = random.randint(0, USER_ID)
        userId2 = random.randint(0, USER_ID)
        date = datetime.date(start=2010, end=2017, fmt=DATE_FORMAT) + " " + datetime.time()
        yield toCsvString(userId1, userId2, date)

def generateLikes(n=random.randint(1,10)):
    global POST_ID
    global USER_ID
    for p in range(n):
        userId = random.randint(0, USER_ID)
        postId = random.randint(0, POST_ID)
        date = datetime.date(start=2010, end=2017, fmt=DATE_FORMAT) + " " + datetime.time()
        yield toCsvString(userId, postId, date)

def generatePersons(n=1000):
    global USER_ID
    for i in range(n):
        USER_ID = i
        name, surname = person.full_name().split(' ')    
        date = datetime.date(start=1960, end=2005, fmt=DATE_FORMAT)
        yield i, toCsvString(str(i), name, surname, date)
    
        
def generateData(path):
    try:
        global USER_ID
        USERS_HEADER = 'id,name,surname,birthdate\n'
        users = open(os.path.join(path, 'users.csv'), 'w')
        users.writelines(USERS_HEADER)
        
        POSTS_HEADER = 'id,userid,text,timestamp\n'
        posts = open(os.path.join(path, 'posts.csv'), 'w')
        posts.write(POSTS_HEADER)
        
        LIKES_HEADER = 'userid,postid,timestamp\n'
        likes = open(os.path.join(path, 'likes.csv'), 'w')
        likes.write(LIKES_HEADER)
        
        FRIENDSHIPS_HEADER = 'userid1,userid2,timestamp\n'
        friendships = open(os.path.join(path, 'friendships.csv'), 'w')
        friendships.write(FRIENDSHIPS_HEADER)
        
        for userId, person in generatePersons():
            users.write(person)
            for post in generatePosts(userId):
                posts.write(post)
        for x in range(1000):
            for like in generateLikes():
                likes.write(like)
            for friendship in generateFriendships():
                friendships.write(friendship)
)
    finally:
        [x.close() for x in [users, posts, likes, friendships]]    
    