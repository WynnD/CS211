#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

enum STATUS {
	none,
	called,
	waiting
}

typedef struct Group {
	char* group_name;
	int group_size = 0;
	enum STATUS status = none;
	GroupPtr next = NULL;
} Group;

typedef Group* GroupPtr;

void addToList(GroupPtr head, int size, char* name, bool inStore) {
	// add a new node to the end of linked list "head"
	GroupPtr temp = (GroupPtr) malloc(sizeof(Group));
	temp->group_name = (char*) malloc(sizeof(char)*(strlen(name)+1));
	strcpy(temp->group_name, name);
	temp->group_size = size;
	temp->status = (inStore?waiting:called);
	temp->next = NULL;
	while (head.next != NULL) {
		head = head->next;
	}

	head->next = temp;

	return;
}

bool doesNameExist(GroupPtr head, char* name) {
	while (head->next != NULL) {
		if (!strcmp(name,head->group_name))
			return true;
		head = head->next;
	}

	return false;
}

bool updateStatus(GroupPtr head, char* name) {
	while (strcmp(name, head->group_name) && head->next != NULL) {
		head = head->next;
	}

	if (strcmp(head->group_name, name) && head->status < 2) {
		head->status = waiting;
		return true; 
	}

	return false;
}

char* retrieveAndRemove(GroupPtr head, int size) {

	GroupPtr trailer;

	while (head->group_size > size && head != NULL) {
		trailer = head;
		head = head->next;
	}

	if (head != NULL) {
		char* group_name = (char*) malloc(sizeof(char)*(strlen(head->group_name)+1));
		strcpy(group_name,head->group_name);
		trailer->next = head->next;
		free(head);
		return group_name;
	}

	return NULL;
}

int countGroupsAhead(char* name, GroupPtr head) {
	int count = 0;

	while (strcmp(name,head->group_name)) {
		head = head->next;
	}
	while (head->next != NULL) {
		count++;
		head = head->next;
	}

	return count;
}

void displayGroupSizeAhead(char* name, GroupPtr head) {
	printf("In order from first in line to last:\n");
	while (head != NULL) {
		if (!strcmp(head->group_name, name)) {
			break;
		}
		printf("Group size: %d \n", head->group_size);
	}

	return;
}

void displayListInformation(GroupPtr head) {
	int status;
	while(head != NULL) {
		status = head->status;
		printf("Group Name: %s\n", head->group_name);
		printf("Group Size: %d\n", head->group_size);
		printf("Group Status: ");
		switch( status ) {
			case 1:
				printf("Called ahead");
				break;
			case 2:
				printf("In-store");
				break;
			default:
				printf("None");
				break;
		}

		printf("\n");
	}

	return;
}

