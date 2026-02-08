package cmd

import (
	"fmt"

	"github.com/spf13/cobra"
)

// Version information (set by ldflags during build)
var (
	Version   = "dev"
	Commit    = "unknown"
	BuildTime = "unknown"
)

// versionCmd represents the version command
var versionCmd = &cobra.Command{
	Use:   "version",
	Short: "Print the version number of DNF Server",
	Long:  `All software has versions. This is DNF Server's.`,
	Run: func(cmd *cobra.Command, args []string) {
		fmt.Printf("DNF Go Server\n")
		fmt.Printf("Version:    %s\n", Version)
		fmt.Printf("Commit:     %s\n", Commit)
		fmt.Printf("Build Time: %s\n", BuildTime)
	},
}

func init() {
	rootCmd.AddCommand(versionCmd)
}
